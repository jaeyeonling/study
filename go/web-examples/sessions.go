package main

import (
	"fmt"
	"net/http"

	"github.com/gorilla/sessions"
)

var (
	// key must be 16, 24 or 32 bytes long (AES-128, AES-192 or AES-256)
	cookieName      = "auth-cookie-name"
	sessionAuthName = "authenticated"
	key             = []byte("super-secret-key")
	store           = sessions.NewCookieStore(key)
)

func secret(rw http.ResponseWriter, r *http.Request) {
	session, err := store.Get(r, cookieName)
	if err != nil {
		http.Error(rw, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	// check if user is authenticated
	if auth, ok := session.Values[sessionAuthName].(bool); !ok || !auth {
		http.Error(rw, http.StatusText(http.StatusForbidden), http.StatusForbidden)
		return
	}

	fmt.Fprintln(rw, "Hello World!")
}

func login(rw http.ResponseWriter, r *http.Request) {
	session, err := store.Get(r, cookieName)
	if err != nil {
		http.Error(rw, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	// authentication goes here

	session.Values[sessionAuthName] = true
	session.Save(r, rw)
}

func logout(rw http.ResponseWriter, r *http.Request) {
	session, err := store.Get(r, cookieName)
	if err != nil {
		http.Error(rw, http.StatusText(http.StatusInternalServerError), http.StatusInternalServerError)
		return
	}

	session.Values[sessionAuthName] = false
	session.Save(r, rw)
}

func main() {
	http.HandleFunc("/secret", secret)
	http.HandleFunc("/login", login)
	http.HandleFunc("/logout", logout)

	http.ListenAndServe(":80", nil)
}
