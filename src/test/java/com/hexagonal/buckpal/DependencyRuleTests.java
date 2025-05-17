package com.hexagonal.buckpal;

import com.hexagonal.buckpal.archunit.HexagonalArchitecture;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

class DependencyRuleTests {

    @Test
    void domainModelDoesNotDependOnOutside() {
        noClasses()
                .that()
                .resideInAPackage("com.hexagonal.buckpal.application.domain.model..")
                .should()
                .dependOnClassesThat()
                .resideOutsideOfPackages(
                        "com.hexagonal.buckpal.application.domain.model..",
                        "lombok..",
                        "java.."
                )
                .check(new ClassFileImporter()
                        .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
                        .importPackages("com.hexagonal.buckpal.."));
    }

    @Test
    void validateRegistrationContextArchitecture() {
        HexagonalArchitecture.basePackage("com.hexagonal.buckpal")

                .withDomainLayer("application.domain")

                .withAdaptersLayer("adapter")
                .incoming("in.web")
                .outgoing("out.persistence")
                .and()

                .withApplicationLayer("application")
                .incomingPorts("port.in")
                .outgoingPorts("port.out")
                .and()

                .withConfiguration("configuration")
                .check(new ClassFileImporter()
                        .importPackages("com.hexagonal.buckpal.."));
    }
}
