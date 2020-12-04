export default function formatDate(text) {
    return((new Date(text)).toISOString().split('T')[0])
}