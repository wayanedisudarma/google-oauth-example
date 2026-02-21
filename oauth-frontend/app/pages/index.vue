<script setup>
import { generateCodeVerifier, generateCodeChallenge } from "~/utils/pkce"
const config = useRuntimeConfig()

const login = async () => {

  const verifier = generateCodeVerifier()
  const challenge = await generateCodeChallenge(verifier)
  const clientId = config.public.oidcClientId

  localStorage.setItem("pkce_verifier", verifier)

  const params = new URLSearchParams({
    client_id: clientId,
    redirect_uri: "http://localhost:3000/callback",
    response_type: "code",
    scope: "openid email profile",
    code_challenge: challenge,
    code_challenge_method: "S256",
    prompt: "select_account"
  })

  window.location.href =
      `https://accounts.google.com/o/oauth2/v2/auth?${params}`
}
</script>

<template>
  <button @click="login">Login with Google</button>
</template>
