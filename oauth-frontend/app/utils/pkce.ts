export function generateCodeVerifier() {
    const array = new Uint32Array(32)
    window.crypto.getRandomValues(array)
    return Array.from(array, dec => dec.toString(16)).join('')
}

export async function generateCodeChallenge(verifier: string) {
    const data = new TextEncoder().encode(verifier)
    const digest = await crypto.subtle.digest('SHA-256', data)
    return btoa(String.fromCharCode(...new Uint8Array(digest)))
        .replace(/\+/g, '-')
        .replace(/\//g, '_')
        .replace(/=+$/, '')
}
