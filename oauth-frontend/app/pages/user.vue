<script setup lang="ts">
definePageMeta({
  middleware: "auth"
})

const { data: user, error } = await useFetch(
    "http://localhost:8080/api/user",
    {
      method: "GET",
      headers: {
        Authorization: `Bearer ${process.client ? localStorage.getItem("access_token") : ""}`
      }
    }
)

if (error.value) {
  navigateTo("/")
}
</script>

<template>
  <div>
    <h1>User Page</h1>

    <div v-if="!user">
      Loading...
    </div>

    <div v-else>
      <p><b>ID:</b> {{ user.data.id }}</p>
      <p><b>Name:</b> {{ user.data.name }}</p>
      <p><b>Email:</b> {{ user.data.email }}</p>
    </div>
  </div>
</template>
