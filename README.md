# validator-core
객체의 유효성을 확인하기 위한 주요 인터페이스 및 클래스

## 1. Installation
OpenJDK 11 이상
#### 1.1. maven
    <repositories>
        <repository>
            <id>jcenter</id>
            <url>https://jcenter.bintray.com/</url>
        </repository>
    </repositories>
    <dependencies>
        <dependency>
            <groupId>io.github.cube8540</groupId>
            <artifactId>validator-core</artifactId>
            <version>1.1.1</version>
        </dependency>
    </dependencies>
#### 1.2. Gradle
    repositories {
        jcenter()
    }
    dependencies {
        compile 'io.github.cube8540:validator-core:1.1.1'
    }

## 2. Getting Started
#### 2.1. Validator + ValidationRule 을 이용한 객체 유효성 검사 방법
```
// 유효성 검사 규칙을 구현
public class YourValidationRule implements ValidationRule<YourClass> {
    @Override
    public boolean isValid(YourClass object) {
        // 유효성 검사 결과 반환 (유효할시 true 유효하지 않을시 false)
    }

    @Override
    public ValidationError error() {
        return new ValidationError(PROPERTY, ERROR_MESSAGE);
    }
}

// 유효성 검사후 검사 결과를 얻기
ValidationResult result = Validator.of(yourObject)
    .registerRule(new YourValidationRule())
    .getResult();
```
#### 2.2. ValidationSpecification 구현 방법
```
// AbstractValidationSpecification 을 상속받아 구현한다.
public class YourValidationSpecification extends AbstractValidationSpecification<YourClass> {
    @Override
    public boolean isValid(YourClass object) {
        // 유효성 검사 결과 반환 (유효할시 true 유효하지 않을시 false)
    }
}

// 유효성 검사 명세서를 이용하여 규칙을 구현
public class YourValidationRule implements ValidationRule<YourClass> {

    private final Specification<YourClass> specification;

    pubic YourValidationRule(Specification<YourClass> specification) {
        this.specification = specification;
    }
 
    @Override
    public boolean isValid(YourClass object) {
        return specification.isValid(object);
    }

    @Override
    public ValidationError error() {
        return new ValidationError(PROPERTY, ERROR_MESSAGE);
    }
}

// 유효성 검사 명세서를 조합
ValidationRule<YourClass> specification = new YourValidationSpecification1()
        .and(YourValidationSpecification2())
        .or(YourValidationSpecification3())
        ...;

// 유효성 검사후 검사 결과를 얻기
ValidationResult result = Validator.of(yourObject)
    .registerRule(new YourValidationRule(specification))
    .getResult();
```

## 3. License
```
           DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                   Version 2, December 2004

Copyright (C) 2019 cube8540 <cube8540@gmail.com>

Everyone is permitted to copy and distribute verbatim or modified
copies of this license document, and changing it is allowed as long
as the name is changed.

DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION
0. You just DO WHAT THE FUCK YOU WANT TO.
```
