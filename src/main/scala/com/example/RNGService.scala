package com.example

import spray.http.MediaTypes._
import spray.routing.HttpService

/**
 * Created by loande on 9/26/15.
 */
trait RNGService extends HttpService {

  val rngRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    }
}