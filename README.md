# evaluacion-service-grupo03

Microservicio de evaluación crediticia para el sistema FintechLoan, desarrollado como parte de la T2 del curso Desarrollo de Aplicaciones Web II.

Se encarga de consultar el scoring de los clientes, clasificar su nivel de riesgo y determinar su capacidad de endeudamiento. La información obtenida será utilizada por el microservicio de solicitudes para evaluar la aprobación de préstamos.

## Tecnologías utilizadas

- Java 25
- Spring Boot 4.1.1
- Spring Cloud 2025.1.3
- Spring Web
- Eureka Discovery Client
- Spring Cloud Config Client
- Spring Boot Actuator
- Lombok
- Maven

## Estructura del proyecto

```text
src/main/java/com/cibertec/evaluacion/
├── controller/
│   └── EvaluacionController.java
├── data/
│   └── ClienteData.java
├── dto/
│   ├── ClienteEvaluacionDTO.java
│   └── ScoringRiesgoDTO.java
├── service/
│   └── EvaluacionService.java
└── EvaluacionServiceGrupo03Application.java
```

## Endpoints

**Consultar scoring crediticio**

`GET /api/v1/evaluaciones/scoring/{clienteId}`

Ejemplo de respuesta:

```json
{
  "clienteId": 1,
  "puntajeCrediticio": 780,
  "nivelRiesgo": "BAJO",
  "capacidadMaxEndeudamiento": 2000.0,
  "dictamenRiesgo": "APTO"
}
```

**Consultar evaluación del cliente**

`GET /api/v1/evaluaciones/cliente/{clienteId}`

Ejemplo de respuesta:

```json
{
  "clienteId": 1,
  "nombreCliente": "Carlos Ramirez",
  "dni": "12345678",
  "scoringActual": {
    "clienteId": 1,
    "puntajeCrediticio": 780,
    "nivelRiesgo": "BAJO",
    "capacidadMaxEndeudamiento": 2000.0,
    "dictamenRiesgo": "APTO"
  },
  "estadoCrediticioGlobal": "APTO"
}
```

## Evaluación crediticia

Para las pruebas se definieron cinco clientes con puntajes e ingresos mensuales simulados.

El nivel de riesgo se clasifica de la siguiente manera:

| Puntaje | Riesgo |
|---|---|
| 750 a más | BAJO |
| 650–749 | MEDIO |
| 500–649 | ALTO |
| Menor a 500 | CRITICO |

Se considera apto al cliente que alcanza el puntaje mínimo configurado y presenta riesgo BAJO o MEDIO.

La capacidad de endeudamiento se obtiene aplicando el factor configurado al ingreso mensual del cliente. Los valores utilizados son referenciales y no corresponden a información real de Infocorp.

## Configuración

| Servicio | Puerto |
|---|---|
| Evaluación Service | 8084 |
| Eureka Server | 8761 |
| Config Server | 8888 |
| API Gateway | 8070 |

El microservicio incluye las dependencias y propiedades necesarias para integrarse con Eureka y Config Server. Estas conexiones deben verificarse durante las pruebas de integración.

## Ejecución

Desde la raíz del proyecto:

```powershell
.\mvnw.cmd spring-boot:run
```

Para probar directamente los endpoints:

```text
http://localhost:8084/api/v1/evaluaciones/scoring/1
http://localhost:8084/api/v1/evaluaciones/cliente/1
```

## Integración

El servicio de solicitudes utilizará OpenFeign para consultar la evaluación crediticia. A partir del nivel de riesgo y la capacidad de endeudamiento recibidos, determinará si corresponde aprobar o rechazar una solicitud.

## Información académica

- **Instituto:** Cibertec
- **Curso:** Desarrollo de Aplicaciones Web II
- **Evaluación:** T2
- **Grupo:** 03
- **Responsable:** Jhaser Alexander Campos Castañeda

**Repositorio del grupo:** https://github.com/Cibertec-DAWII-T2-Grupo03