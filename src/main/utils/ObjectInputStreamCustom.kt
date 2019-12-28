package main.utils

import java.io.InputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class ObjectInputStreamCustom(`in`: InputStream?) : ObjectInputStream(`in`) {
    override fun readStreamHeader() {
    }
}