export default defineNuxtRouteMiddleware(() => {
    if (process.server) return

    const accessToken = localStorage.getItem("access_token")
    const refreshToken = localStorage.getItem("refresh_token")

    if (!accessToken || !refreshToken) {
        return navigateTo("/")
    }
})
