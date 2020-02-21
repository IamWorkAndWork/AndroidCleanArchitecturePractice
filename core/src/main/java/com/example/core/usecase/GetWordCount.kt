package com.example.core.usecase

import com.example.core.data.Note

class GetWordCount {

    operator fun invoke(note: Note): Int {
        val titleCount = getCount(note.title)
        val contentCount = getCount(note.content)
        return titleCount + contentCount
    }

    private fun getCount(str: String): Int {
        return str.split("", "\n")
            .filter {
                it.contains(Regex(".*[a-zA-Z].*"))
            }
            .count()
    }

}