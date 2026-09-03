package com.roshanadke.wishnest.repo

class WishRepository(
    private val tempFile: TempFile
) {
    fun getTempFileName(): String {
        return tempFile.createTempFile()
    }
}