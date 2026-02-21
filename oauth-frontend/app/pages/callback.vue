<script setup>
import axios from "axios"

const route = useRoute()

onMounted(async () => {

  const code = route.query.code
  const verifier = localStorage.getItem("pkce_verifier")

  const res = (await axios.post("http://localhost:8080/auth/google", {
    code: code,
    code_verifier: verifier
  })).data

  localStorage.setItem("access_token", res.data.access_token)
  localStorage.setItem("refresh_token", res.data.refresh_token)

  navigateTo("/user")
})
</script>

<template>
  <div>Processing login...</div>
</template>
