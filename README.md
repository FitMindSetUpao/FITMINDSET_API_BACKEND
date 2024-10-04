# **FitMindSet_UPAO**
**Proyecto: Seguimiento de Hábitos Saludables**

## **Descripción**
FitMindSet_UPAO es una plataforma diseñada para facilitar la adopción y el seguimiento de hábitos saludables. Está dirigida a usuarios que desean mejorar su estilo de vida, pacientes con condiciones crónicas que requieren monitoreo continuo, y comunidades interesadas en el bienestar colectivo. La plataforma ofrece un enfoque personalizado y accesible, con herramientas respaldadas por profesionales de la salud, proporcionando recomendaciones ajustadas, monitoreo en tiempo real y motivación constante.

## **Características Principales**
- **Monitoreo de Hábitos**: Seguimiento personalizado de áreas clave como alimentación, ejercicio y sueño.
- **Recomendaciones Basadas en Datos**: Propuestas personalizadas según las necesidades del usuario y su progreso.
- **Motivación Continua**: Recordatorios automáticos y notificaciones personalizadas para mantener la constancia en los hábitos.
- **Desafíos de Bienestar**: Participación en retos diseñados para fomentar hábitos saludables.
- **Acceso a Contenidos Especializados**: Recursos validados por profesionales de la salud.
- **Soporte Comunitario**: Herramientas para organizar actividades de bienestar y compartir experiencias en comunidades.

## **Tipos de Usuarios**
1. **Personas que desean mejorar su estilo de vida**:
   - Monitoreo de progreso diario en ejercicio, alimentación y sueño.
   - Recordatorios y desafíos para promover hábitos saludables.
   - Acceso a información médica confiable y personalizada.

2. **Pacientes que requieren seguimiento continuo**:
   - Monitoreo en tiempo real de indicadores clave de salud.
   - Integración de horarios de alimentación, medicación y rutinas de ejercicio.
   - Recomendaciones y recordatorios personalizados.

3. **Comunidades interesadas en el bienestar colectivo**:
   - Organización de actividades colectivas (ejercicio, meditación).
   - Espacios para compartir experiencias y recomendaciones.
   - Monitoreo del progreso colectivo.

## **Tecnologías Utilizadas**
- **Frontend**: ReactJS, HTML5, CSS3
- **Backend**: Node.js, Express
- **Base de Datos**: MongoDB
- **Autenticación**: JWT (JSON Web Tokens)
- **Notificaciones**: Firebase Cloud Messaging
- **Despliegue**: Docker, Kubernetes

## **Asignación de Historias de Usuario**
Cada historia de usuario se asigna a un módulo y tiene un identificador único:

| **Integrante**                       | **ID**   | **Módulo**                  | **Historia de Usuario**                                                                                                                                     | **Tipo**   |
|--------------------------------------|----------|-----------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------|------------|
| Saavedra Arroyo Sebastián Alonso     | EPC-1    | Gestión de Usuarios          | Como usuario quiero poder registrarme especificando mis objetivos de salud y preferencias para recibir recomendaciones personalizadas.                       | CRUD       |
| Saavedra Arroyo Sebastián Alonso     | EPC-2    | Gestión de Usuarios          | Como usuario registrado quiero poder iniciar sesión con las credenciales para acceder a mi perfil y progreso.                                                | CORE       |
| Saavedra Arroyo Sebastián Alonso     | EPC-3    | Gestión de Usuarios          | Como usuario quiero acceder a mi perfil con mi cuenta de correo electrónico para tener mayor seguridad.                                                      | CORE       |
| Saavedra Arroyo Sebastián Alonso     | EPC-4    | Gestión de Usuarios          | Como usuario quiero poder recuperar mis credenciales para poder acceder nuevamente a mi perfil sin perder mi progreso en las metas de salud.                  | CORE       |
| Saavedra Arroyo Sebastián Alonso     | EPC-5    | Gestión de Usuarios          | Como usuario quiero actualizar mi perfil para mantener mis datos personales y objetivos de salud al día.                                                     | CRUD       |
| Jimenez Atoche Sthefano Gabriel      | EPC-7    | Gestión de Usuarios          | Como usuario quiero establecer preferencias de notificaciones para recibir recordatorios y motivaciones según mis necesidades.                               | CRUD/CORE  |
| Jimenez Atoche Sthefano Gabriel      | EPC-8    | Gestión de Usuarios          | Como usuario quiero eliminar mi cuenta si ya no deseo utilizar la plataforma para de esta manera asegurar la privacidad de mis datos.                        | CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-11   | Metas y Seguimiento          | Como usuario quiero registrar lo que hago día a día en relación a mi dieta equilibrada y actividad física para mantener una organización de mis objetivos.   | CORE       |
| Prieto Meléndez Alexander Antonio    | EPC-12   | Reportes                    | Como usuario quiero visualizar un reporte estadístico de mi progreso diario para identificar áreas de mejora y ajustar mis hábitos.                          | CORE       |
| Baylón Toledo Diogho Matteo          | EPC-19   | Reportes/Seguimiento         | Como usuario quiero poder ver una barra de progreso que muestre mi avance hacia mis metas para tener una referencia visual rápida de mi desempeño.            | Reporte    |
| Baylón Toledo Diogho Matteo          | EPC-21   | Pagos en Línea               | Como usuario quiero poder suscribirme a contenido adicional para tener acceso continuo a recursos especializados y mejorar mi experiencia en la plataforma.   | Pago CORE  |
| Baylón Toledo Diogho Matteo          | EPC-22   | Pagos en Línea               | Como usuario quiero poder comprar recursos exclusivos de manera individual para acceder a información especializada.                                          | Pago CORE  |
| Prieto Meléndez Alexander Antonio    | EPC-23   | Gestión de Usuarios          | Como usuario quiero que la página web sea interactiva y fácil de usar para que mi experiencia sea rápida y sencilla al gestionar mis hábitos saludables.      | CORE       |
| Prieto Meléndez Alexander Antonio    | EPC-24   | Recursos                    | Como usuario quiero que la página esté respaldada por expertos en el área para sentir confianza en la información que utilizo para mejorar mi salud.         | CORE       |
| Prieto Meléndez Alexander Antonio    | EPC-25   | Pagos en Línea               | Como usuario quiero tener opciones de pago accesibles para que el proceso de suscripción sea conveniente y sin complicaciones.                               | CRUD       |
| Jimenez Atoche Sthefano Gabriel      | EPC-26   | Recursos                    | Como usuario quiero filtros de recomendación sobre las horas del sueño, comidas y ejercicios para gestionar el hábito.                                      | CRUD       |
| Jimenez Atoche Sthefano Gabriel      | EPC-27   | Recursos                    | Como usuario quiero que la página pueda descargar los recursos que tengo disponibles para optimizar mi tiempo y seguir mis hábitos para alcanzar mi meta.    | CORE       |
| Lescano Gutiérrez Jaime Rafael       | EPC-28   | Gestión de Usuarios          | Como usuario quiero registrar mi peso, altura y porcentaje de grasa para tener un registro de progreso según mi alimentación.                                | CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-29   | Metas y Seguimiento          | Como usuario quiero saber qué tipo de ejercicio puedo hacer según mis gustos y necesidades para que me sea menos difícil seguir con mis rutinas.             | CORE       |
| Lescano Gutiérrez Jaime Rafael       | EPC-30   | Pagos en Línea               | Como usuario quiero tener la opción de pagar la versión premium de la aplicación dependiendo de los planes que esta ofrece para pagar el que mejor me convenga.| CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-31   | Recursos                    | Como usuario quiero poder tener comunicación con un profesional de la salud en caso tenga un caso en particular para tener información específica.            | CORE       |
| Lescano Gutiérrez Jaime Rafael       | EPC-32   | Gestión de Usuarios          | Como usuario quiero poder registrar recetas en la aplicación para que sea más simple el registro de alimentos.                                               | CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-33   | Gestión de Usuarios          | Como usuario quiero gestionar mi espacio de recetas de platillos saludables.                                                                                 | CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-34   | Pagos en Línea               | Como usuario quiero poder tener acceso a la información de lo que ofrece la versión premium de la aplicación.                                                | CRUD       |
| Lescano Gutiérrez Jaime Rafael       | EPC-35   | Recursos                    | Como usuario quiero tener acceso a un foro de usuarios para consultar, explicar y debatir sobre temas de la salud.                                           | CORE       |

