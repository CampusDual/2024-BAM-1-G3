package com.campusdual_grupo3.bookandgo.data.datasource.mock
//
//import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.ExperienceRemoteDataSource
//import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.CategoryDto
//import com.campusdual_grupo3.bookandgo.data.datasource.remote.experience.dto.ExperienceDto
//
//import java.time.LocalDate
//import javax.inject.Inject
//
//class ExperienceMockRemoteDataSourceImpl @Inject constructor(
//
//) : ExperienceRemoteDataSource {
//    override suspend fun getExperiences(): List<ExperienceDto> {
//        return listOf(
//            ExperienceDto(
//                id = 1,
//                name = "Experiencia Aventura",
//                description = "Senderismo de alta montaña, con rutas como la Senda de los Cazadores en el Parque Nacional de Ordesa y Monte Perdido",
//                price = 100,
//                duration = 2,
//                dateTo = LocalDate.of(2023,12,1),
//                dateFrom = LocalDate.of(2023,12,31),
//                location = "Pirineos",
//                capacity = 10,
//                stock = 5,
//                availability = true,
//
//                reviews = listOf(
//                    RewiewDto(
//                        id = 1,
//                        rating = 4.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    ),RewiewDto(
//                        id = 2,
//                        rating = 4.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    )
//                ),
//                category = 1,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                createAt = LocalDate.of(2024,3,1),
//                updateAt = null
//            ),
//            ExperienceDto(
//                id = 2,
//                name = "Experiencia Cultura",
//                description = "Paseo por el casco antiguo de Toledo, Patrimonio de la Humanidad, con su mezcla de culturas cristiana, judía y musulmana.",
//                price = 100,
//                duration = 2,
//                dateTo = LocalDate.of(2023,12,1),
//                dateFrom = LocalDate.of(2023,12,31),
//                location = "Toledo",
//                capacity = 10,
//                stock = 5,
//                availability = true,
//
//                reviews = listOf(
//                    RewiewDto(
//                        id = 1,
//                        rating = 3.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    ),RewiewDto(
//                        id = 2,
//                        rating = 4.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    )
//                ),
//                category = 4,
//                image = "https://estaticos-cdn.prensaiberica.es/clip/beec0e03-4c3a-4da1-a898-9d49ba87e11c_original-libre-aspect-ratio_default_0.webp",
//                createAt = LocalDate.of(2022,2,1),
//                updateAt = null
//            ),
//            ExperienceDto(
//                id = 3,
//                name = "Experiencia Gastronomía",
//                description = "Experiencia de cocina molecular, donde los ingredientes se transforman en texturas y sabores sorprendentes.",
//                price = 100,
//                duration = 2,
//                dateTo = LocalDate.of(2023,12,1),
//                dateFrom = LocalDate.of(2023,12,31),
//                location = "Pontevedra",
//                capacity = 10,
//                stock = 5,
//                availability = true,
//
//                reviews = listOf(
//                    RewiewDto(
//                        id = 1,
//                        rating = 2.5,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    ),
//                    RewiewDto(
//                        id = 2,
//                        rating = 4.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    )
//                ),
//                category = 2,
//                image = "https://estaticos-cdn.prensaiberica.es/clip/e656c3ab-a9d2-4773-bc69-22a9a40f80f0_original-libre-aspect-ratio_default_0.webp",
//                createAt = LocalDate.of(2023,1,1),
//                updateAt = null
//            ),
//            ExperienceDto(
//                id = 4,
//                name = "Experiencia Aventura",
//                description = "Peregrinación a pie o en bicicleta por las diferentes rutas del Camino de Santiago, una experiencia espiritual y cultural.",
//                price = 100,
//                duration = 2,
//                dateTo = LocalDate.of(2023,12,1),
//                dateFrom = LocalDate.of(2023,12,31),
//                location = "Santiago de Compostela",
//                capacity = 10,
//                stock = 5,
//                availability = true,
//
//                reviews = listOf(
//                    RewiewDto(
//                        id = 1,
//                        rating = 4.5,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    ),
//                    RewiewDto(
//                        id = 2,
//                        rating = 4.2,
//                        comment = " ¡Una experiencia perfecta para escapar del estrés y recargar energías!\"",
//                        createAt = LocalDate.now(),
//                        updateAt = null
//
//                    )
//                ),
//                category = 1,
//                image = "https://guiauniversitaria.mx/wp-content/uploads/2019/01/carreras-con-las-que-podrias-viajar.jpg.webp",
//                createAt = LocalDate.of(2021,2,1),
//                updateAt = null
//            ),
//
//
//            )
//    }
//
//    override suspend fun getExperienceById(id: Int): ExperienceDto? {
//        return getExperiences().find { it.id == id }
//
//    }
//
//    override suspend fun getRewiewsByExperienceId(experienceId: Int): List<RewiewDto> {
//       return getExperiences().first().reviews ?: emptyList()
//    }
//
//    override suspend fun getCategories(): List<CategoryDto> {
//        return listOf(
//            CategoryDto(
//                id = 1,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                name = "Aventura"),
//            CategoryDto(
//                id = 2,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                name = "Gastronomía"),
//            CategoryDto(
//                id = 3,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                name = "Vida Nocturna"),
//            CategoryDto(
//                id = 4,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                name = "Cultura"),
//            CategoryDto(
//                id = 5,
//                image = "https://i.blogs.es/161dfa/simon-migaj-yui5vfkhuzs-unsplash/1366_2000.webp",
//                name = "Relax"
//            )
//        )
//    }
//
//
//
//    override suspend fun getExperiencesByCategory(categoryId: Int): List<ExperienceDto> {
//        return getExperiences().filter { it.category == categoryId }
//    }
//
//
//}