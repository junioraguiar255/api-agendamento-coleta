```markdown
# Projeto de Agendamento de Coleta

Este projeto consiste em uma API para agendamento de coletas, desenvolvida utilizando Spring Boot. 
O objetivo principal é oferecer uma solução escalável e confiável, com suporte a integração contínua e deployment contínuo.

## Funcionalidades Implementadas

### CI/CD Pipeline

Foi implementado um pipeline de CI/CD utilizando **Jenkins**, **GitHub** e **Docker**. 
Este pipeline automatiza o processo de integração e entrega contínua, garantindo que o código seja compilado, 
testado e implantado em ambientes de staging e produção.

#### Estrutura do Pipeline

O pipeline é composto pelas seguintes etapas:

1. **Checkout**: Faz o checkout do código da branch `master` do repositório GitHub.
2. **Build**: Compila o projeto utilizando Maven, ignorando os testes (`mvn clean package -DskipTests`).
3. **Docker Build**: Constrói a imagem Docker para a aplicação.
4. **Deploy to Staging**: Realiza o deployment em um ambiente de staging, se a construção anterior foi bem-sucedida.
5. **Test**: Executa os testes da aplicação.
6. **Deploy to Production**: Realiza o deployment em um ambiente de produção, se a execução anterior foi bem-sucedida.

#### Exemplo do Código do Pipeline

```groovy
pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'my-image'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'https://github.com/brunolei145/api-agendamento-coleta.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }

        stage('Docker Build') {
            steps {
                sh '''
                #!/bin/bash
                docker build -t $DOCKER_IMAGE .
                '''
            }
        }

        stage('Deploy to Staging') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                echo 'Deploying to staging environment...'
            }
        }

        stage('Test') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                echo 'Running tests...'
                sh 'mvn test'
            }
        }

        stage('Deploy to Production') {
            when {
                expression { currentBuild.resultIsBetterOrEqualTo('SUCCESS') }
            }
            steps {
                echo 'Deploying to production...'
            }
        }
    }

    post {
        always {
            echo 'Pipeline finalizada, limpando workspace.'
            cleanWs()
        }

        success {
            echo 'Pipeline executada com sucesso.'
        }

        failure {
            echo 'Pipeline falhou.'
            sh 'env'
        }
    }
}
```

### Containerização e Orquestração

A aplicação foi containerizada utilizando **Docker**, garantindo que possa ser executada de forma consistente em qualquer ambiente. 
Para orquestração dos serviços, **Docker Compose** ou **Kubernetes** pode ser empregado.

## Requisitos

- Java 11 ou superior
- Maven
- Docker
- Jenkins
- Acesso ao GitHub

## Como Executar o Projeto Localmente

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/brunolei145/api-agendamento-coleta.git
   cd api-agendamento-coleta
   ```

2. **Compile o projeto:**
   ```bash
   mvn clean package
   ```

3. **Execute a aplicação:**
   ```bash
   java -jar target/nome-do-arquivo.jar
   ```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para enviar um pull request ou abrir um issue para 
discutir melhorias.

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).
```
