/*
 * Copyright 2017-2018 Aleksey Fomkin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package korolev.server

import korolev.Context.File
import korolev.{Async, FormData, LazyBytes}

import scala.util.Try

private[server] abstract class KorolevSession[F[_]: Async] {
  def publish(message: String): F[Unit]
  def nextMessage: F[String]
  def destroy(): F[Unit]
  def resolveFormData(descriptor: String, formData: Try[FormData]): Unit
  def resolveFile(descriptor: String, file: File[LazyBytes[F]], total: Int): Unit
}
