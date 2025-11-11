package com.volia.example.userservice

import com.volia.example.userservice.dto.TeacherDto
import com.volia.example.userservice.exception.EmailAlreadyExistsException
import com.volia.example.userservice.exception.UserNotFoundException
import com.volia.example.userservice.mapper.TeacherMapper
import com.volia.example.userservice.model.Teacher
import com.volia.example.userservice.repository.TeacherRepository
import com.volia.example.userservice.service.TeacherService
import spock.lang.Specification
import spock.lang.Subject

class TeacherServiceSpec extends Specification {

    def repository = Mock(TeacherRepository)
    def mapper = Mock(TeacherMapper)

    @Subject
    TeacherService service = new TeacherService(repository, mapper)

    def "should create teacher successfully"() {
        given:
        def dto = new TeacherDto(
                null,
                "John Doe",
                "john.doe@example.com",
                "Math teacher"
        )
        def teacher = new Teacher(
                name: dto.name(),
                email: dto.email(),
                bio: dto.bio()
        )

        repository.existsByEmail(dto.email()) >> false
        mapper.fromDto(dto) >> teacher
        repository.save(teacher) >> teacher
        mapper.toDto(teacher) >> dto

        when:
        def result = service.create(dto)

        then:
        1 * mapper.fromDto(dto) >> teacher
        1 * repository.existsByEmail("john.doe@example.com") >> false
        1 * repository.save(teacher) >> teacher
        1 * mapper.toDto(teacher) >> dto
        0 * _

        result.id() == null
        result.name() == "John Doe"
        result.email() == "john.doe@example.com"
        result.bio() == "Math teacher"
    }


    def "should throw EmailAlreadyExistsException when creating teacher with existing email"() {
        given:
        def dto = new TeacherDto(
                null,
                "Jane Smith",
                "jane@example.com",
                "Physics teacher"
        )
        def teacher = new Teacher(
                name: dto.name(),
                email: dto.email(),
                bio: dto.bio()
        )

        repository.existsByEmail(dto.email()) >> true
        mapper.fromDto(dto) >> teacher

        when:
        service.create(dto)

        then:
        thrown(EmailAlreadyExistsException)
    }

    def "should update teacher successfully"() {
        given:
        def dto = new TeacherDto(
                1L,
                "John Updated",
                "john.updated@example.com",
                "Updated bio"
        )
        def existing = new Teacher(
                id: 1L,
                name: "John Doe",
                email: "john.doe@example.com",
                bio: "Math teacher"
        )
        def updatedDto = dto

        repository.findById(1L) >> Optional.of(existing)
        mapper.dtoEmail(dto) >> dto.email()
        repository.existsByEmail(dto.email()) >> false

        when:
        def result = service.update(1L, dto)

        then:
        1 * mapper.updateFromDto(dto, existing)
        1 * repository.save(existing) >> existing
        1 * mapper.toDto(existing) >> updatedDto
        result.id() == 1L
        result.name() == "John Updated"
        result.email() == "john.updated@example.com"
        result.bio() == "Updated bio"
    }

    def "should throw UserNotFoundException when updating non-existing teacher"() {
        given:
        def dto = new TeacherDto(
                999L,
                "Non Existing",
                "nonexisting@example.com",
                "N/A"
        )
        repository.findById(999L) >> Optional.empty()

        when:
        service.update(999L, dto)

        then:
        thrown(UserNotFoundException)
    }

    def "should get teacher by id"() {
        given:
        def teacher = new Teacher(
                id: 1L,
                name: "John Doe",
                email: "john.doe@example.com",
                bio: "Math teacher"
        )
        def dto = new TeacherDto(
                1L,
                "John Doe",
                "john.doe@example.com",
                "Math teacher"
        )

        repository.findById(1L) >> Optional.of(teacher)
        mapper.toDto(teacher) >> dto

        when:
        def result = service.getById(1L)

        then:
        result.id() == 1L
        result.name() == "John Doe"
        result.email() == "john.doe@example.com"
        result.bio() == "Math teacher"
    }

    def "should throw UserNotFoundException when teacher not found"() {
        given:
        repository.findById(99L) >> Optional.empty()

        when:
        service.getById(99L)

        then:
        thrown(UserNotFoundException)
    }

    def "should delete existing teacher"() {
        given:
        repository.existsById(1L) >> true

        when:
        service.delete(1L)

        then:
        1 * repository.deleteById(1L)
    }


    def "should throw UserNotFoundException when deleting non-existing teacher"() {
        given:
        repository.existsById(999L) >> false

        when:
        service.delete(999L)

        then:
        thrown(UserNotFoundException)
    }
}