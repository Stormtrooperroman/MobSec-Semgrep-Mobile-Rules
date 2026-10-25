// License: Commons Clause License Condition v1.0[LGPL-2.1-only]
package cookie;

public class CookieHTTPOnly {

    public void dangerJavax(javax.servlet.http.HttpServletResponse res) {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setSecure(true);
        cookie.setMaxAge(60);
        // ruleid: gitlab-android-cookie-http-only
        cookie.setHttpOnly(false); // danger
        res.addCookie(cookie);
    }

    // cookie.setHttpOnly(true) is missing
    public void danger2Javax(javax.servlet.http.HttpServletResponse res) {
        javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie("key", "value");
        cookie.setSecure(true);
        cookie.setMaxAge(60);
        // ruleid: gitlab-android-cookie-http-only
        res.addCookie(cookie);
    }

    protected void safeJavax(javax.servlet.http.HttpServletResponse response) {
        javax.servlet.http.Cookie myCookie = new javax.servlet.http.Cookie("key", "value");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(60);
        // rule ok: gitlab-android-cookie-http-only
        response.addCookie(myCookie);
    }

    protected void dangerJakarta(jakarta.servlet.http.HttpServletResponse response) {
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        // ruleid: gitlab-android-cookie-http-only
        myCookie.setHttpOnly(false);
        myCookie.setMaxAge(60);
        response.addCookie(myCookie);
    }

    protected void danger2Jakarta(jakarta.servlet.http.HttpServletResponse response) {
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setMaxAge(60);
        // ruleid: gitlab-android-cookie-http-only
        response.addCookie(myCookie);
    }

    protected void safeJakarta(jakarta.servlet.http.HttpServletResponse response) {
        jakarta.servlet.http.Cookie myCookie = new jakarta.servlet.http.Cookie("key", "value");
        myCookie.setHttpOnly(true);
        myCookie.setMaxAge(60);
        // rule ok: gitlab-android-cookie-http-only
        response.addCookie(myCookie);
    }
}
