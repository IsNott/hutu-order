import Cookies from 'js-cookie'

const TokenKey = 'token'

export function getToken() {
  return Cookies.get(TokenKey)
}

export function getTokenByKey(key) {
  return Cookies.get(key)
}

export function setToken(token) {
  return Cookies.set(TokenKey, token)
}

export function setTokenByCustKey(key, token) {
  return Cookies.set(key, token)
}

export function removeToken() {
  return Cookies.remove(TokenKey)
}
