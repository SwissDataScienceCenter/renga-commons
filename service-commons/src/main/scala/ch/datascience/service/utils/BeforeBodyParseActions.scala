/*
 * Copyright 2017 - Swiss Data Science Center (SDSC)
 * A partnership between École Polytechnique Fédérale de Lausanne (EPFL) and
 * Eidgenössische Technische Hochschule Zürich (ETHZ).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ch.datascience.service.utils

import akka.util.ByteString
import play.api.libs.streams.Accumulator
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by johann on 11/07/17.
  */
trait RefineBeforeBodyParseAction extends ActionBuilder[Request, AnyContent] {

  def refine(rh: RequestHeader): Either[Result, RequestHeader]

  override protected def composeParser[A](bodyParser: BodyParser[A]): BodyParser[A] = new BodyParser[A] {
    def apply(rh: RequestHeader): Accumulator[ByteString, Either[Result, A]] = {
      refine(rh).fold(makeError[A](_).apply(rh), bodyParser)
    }
  }

  private[this] def makeError[A](result: Result): BodyParser[A] = BodyParsers.utils.error( Future.successful( result ) )

}

trait TransformBeforeBodyParseAction extends RefineBeforeBodyParseAction {

  def transform(rh: RequestHeader): RequestHeader

  final def refine(rh: RequestHeader): Either[Result, RequestHeader] = Right(transform(rh))

}

trait FilterBeforeBodyParseAction extends RefineBeforeBodyParseAction {

  def filter(rh: RequestHeader): Option[Result]

  final def refine(rh: RequestHeader): Either[Result, RequestHeader] = filter(rh).toLeft(rh)

}
