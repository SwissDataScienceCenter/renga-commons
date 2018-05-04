/*
 * Copyright 2017-2018 - Swiss Data Science Center (SDSC)
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

package ch.datascience.service.security

import ch.datascience.service.utils.AbstractFilterBeforeBodyParseAction
import com.auth0.jwt.exceptions._
import com.auth0.jwt.{ JWT, JWTVerifier }
import play.api.mvc._

import scala.concurrent.Future
import scala.util.Try
import scala.util.matching.Regex

/**
 * Created by johann on 13/07/17.
 */
object ProfileFilterAction {

  def apply( verifier: JWTVerifier, realm: String, altVerifiers: JWTVerifier* ): ActionBuilder[RequestWithProfile] = {
    TokenFilterAction( verifier, realm, altVerifiers: _* ) andThen ProfileAction
  }

  def apply( verifier: JWTVerifier, altVerifiers: JWTVerifier* ): ActionBuilder[RequestWithProfile] = {
    TokenFilterAction( verifier, realm = "", altVerifiers: _* ) andThen ProfileAction
  }

}
