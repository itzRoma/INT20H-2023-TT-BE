# Team: Seven
## Project: Like from a hungry land...
>This project is created in scope of the test task on hackathon **INT20 2023**

|               | Tool name |
|---------------| ------ |
| Frameworks    | React, Spring Boot |
| Cloud         | AWS |
| Used external API | https://www.themealdb.com/ |

## Requirements to run project locally:
- Node.js 16.x
- Typescript 3.4.5
- Java 17.x
- Docker

## Installation steps
**Backend:**
1. Clone BE project https://github.com/sevenorganization/INT20H-2023-TT-BE
```sh
git clone https://github.com/sevenorganization/INT20H-2023-TT-BE
```

2. Run docker container
```sh
cd INT20H-2023-TT-BE & docker-compose up -d
```

3. Run the project from your IDE (it is preferably using IntelliJ IDEA)
##
**Frontend:**
1. Clone FE project https://github.com/sevenorganization/INT20H-2023-TT-FE
```sh
git clone https://github.com/sevenorganization/INT20H-2023-TT-FE
```

2. Install FE dependencies
```sh
npm run install:frontend
```

3. Run FE project
```sh
cd frontend & npm run start
```

4. Open link in you browser http://localhost:3000/
