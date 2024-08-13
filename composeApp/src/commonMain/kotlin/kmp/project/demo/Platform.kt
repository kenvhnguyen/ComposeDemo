package kmp.project.demo

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform