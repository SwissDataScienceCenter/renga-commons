package ch.datascience.service.security

import java.time.Instant

import play.api.Configuration
import play.api.libs.json._
import play.api.libs.functional.syntax._
import play.api.libs.ws.{ WSAuthScheme, WSClient }

import scala.concurrent.{ ExecutionContext, Future }

class TokenProvider(
    val config: Configuration,
    ws:         WSClient
)(
    implicit
    ec: ExecutionContext
) {

  lazy val clientId: String = config.getString( "client_id" ).get
  lazy val clientSecret: String = config.getString( "client_secret" ).get
  lazy val providerUrl: String = config.getString( "provider.url" ).get

  import TokenProvider._

  def get: Future[String] = currentValidToken match {
    case Some( token ) => Future.successful( token )
    case _ => fetchAccessToken.map { accessToken =>
      currentToken = Some( accessToken )
      accessToken.token
    }
  }

  private[this] var currentToken: Option[AccessToken] = None

  protected def currentValidToken: Option[String] = {
    val now = Instant.now()
    currentToken match {
      case Some( AccessToken( token, expires ) ) if expires.compareTo( now ) < 0 => Some( token )
      case _ => None
    }
  }

  protected def fetchAccessToken: Future[AccessToken] = {
    for {
      response <- ws.url( providerUrl ).withAuth( clientId, clientSecret, WSAuthScheme.BASIC ).post( Map( "grant_type" -> Seq( "client_credentials" ) ) )
    } yield response.status match {
      case 200 => response.json.as[AccessToken]( accessTokenReads )
      case _   => throw new RuntimeException( response.statusText )
    }
  }

}

object TokenProvider {

  case class AccessToken( token: String, expires: Instant )

  lazy val accessTokenReads: Reads[AccessToken] = (
    ( JsPath \ "access_token" ).read[String] and
    ( JsPath \ "expires_in" ).read[Int]
  ) { ( token, duration ) =>
      val expires = Instant.now().plusSeconds( duration - 5 )
      AccessToken( token, expires )
    }

}
