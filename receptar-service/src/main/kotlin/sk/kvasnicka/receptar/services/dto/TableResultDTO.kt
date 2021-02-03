package sk.kvasnicka.receptar.services.dto

data class TableResultDTO<R>(val data: List<R>, val meta: TableMetadata) {

    data class TableMetadata(
        val hasNextPage: Boolean,
        val hasPreviousPage: Boolean,
        val totalPages: Int,
        val totalRows: Long
    ) {

    }
}
