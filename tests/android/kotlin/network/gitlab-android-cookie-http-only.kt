// License: LGPL-3.0 License (c) find-sec-bugs
package cookie

class CookieHTTPOnly {
    fun dangerJavax(res: javax.servlet.http.HttpServletResponse) {
        // ruleid: gitlab-android-cookie-http-only
        val cookie = javax.servlet.http.Cookie("key", "value")
        cookie.setSecure(true)
        cookie.setMaxAge(60)
        cookie.setHttpOnly(false) // danger
        res.addCookie(cookie)
    }

    // cookie.setHttpOnly(true) is missing
    fun danger2Javax(res: javax.servlet.http.HttpServletResponse) {
        // ruleid: gitlab-android-cookie-http-only
        val cookie = javax.servlet.http.Cookie("key", "value")
        cookie.setSecure(true)
        cookie.setMaxAge(60)
        res.addCookie(cookie)
    }

    protected fun safeJavax(response: javax.servlet.http.HttpServletResponse) {
        // rule ok: gitlab-android-cookie-http-only
        val myCookie = javax.servlet.http.Cookie("key", "value")
        myCookie.setHttpOnly(true)
        myCookie.setMaxAge(60)
        response.addCookie(myCookie)
    }

    protected fun dangerJakarta(response: jakarta.servlet.http.HttpServletResponse) {
        // ruleid: gitlab-android-cookie-http-only
        val myCookie = jakarta.servlet.http.Cookie("key", "value")
        myCookie.setHttpOnly(false)
        myCookie.setMaxAge(60)
        response.addCookie(myCookie)
    }

    protected fun danger2Jakarta(response: jakarta.servlet.http.HttpServletResponse) {
        // ruleid: gitlab-android-cookie-http-only
        val myCookie = jakarta.servlet.http.Cookie("key", "value")
        myCookie.setMaxAge(60)
        response.addCookie(myCookie)
    }

    protected fun safeJakarta(response: jakarta.servlet.http.HttpServletResponse) {
        // rule ok: gitlab-android-cookie-http-only
        val myCookie = jakarta.servlet.http.Cookie("key", "value")
        myCookie.setHttpOnly(true)
        myCookie.setMaxAge(60)
        response.addCookie(myCookie)
    }
}