# StackFordge Backend API - PRODUCTION READY ✅

Production-ready Java Spring Boot REST API for StackFordge contact management.

## 🚀 Quick Start

```bash
# Run locally
mvn spring-boot:run

# API runs on http://localhost:8080
```

## ✅ What's Included

- ✅ Contact form submission API
- ✅ H2 in-memory database (development)
- ✅ PostgreSQL ready (production)
- ✅ CORS configured for Vercel
- ✅ Input validation
- ✅ Admin endpoints
- ✅ Health check endpoint
- ✅ Production configuration

## 📡 API Endpoints

### Public Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/contacts` | Submit contact form |
| GET | `/api/contacts/health` | Health check |

### Admin Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/contacts` | Get all contacts |
| GET | `/api/contacts/uncontacted` | Get new inquiries |
| GET | `/api/contacts/{id}` | Get contact by ID |
| PUT | `/api/contacts/{id}/contacted` | Mark as contacted |
| DELETE | `/api/contacts/{id}` | Delete contact |

## 📦 Test API

```bash
# Health check
curl http://localhost:8080/api/contacts/health

# Submit contact
curl -X POST http://localhost:8080/api/contacts \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "projectType": "website",
    "budget": "$5k-$10k",
    "timeline": "1-month",
    "message": "I need a website"
  }'
```

## 🗄 Database

### Development (H2)
- **Console**: http://localhost:8080/h2-console
- **JDBC URL**: `jdbc:h2:mem:stackfordge`
- **Username**: `sa`
- **Password**: (empty)

### Production (PostgreSQL)
Automatically configured via `DATABASE_URL` environment variable.

## ☁️ Deploy to Production

### Option 1: Render.com (FREE - Recommended)

1. Go to [render.com](https://render.com)
2. Create new Web Service
3. Connect GitHub repository
4. Settings:
   - **Build Command**: `mvn clean package`
   - **Start Command**: `java -jar target/stackfordge-backend.jar`
5. Add PostgreSQL database
6. Environment variables:
   ```
   DATABASE_URL=<auto-filled by Render>
   CORS_ORIGINS=https://stackfordge.vercel.app
   ```
7. Deploy!

### Option 2: Railway.app

1. Go to [railway.app](https://railway.app)
2. New Project → Deploy from GitHub
3. Add PostgreSQL plugin
4. Environment variables:
   ```
   CORS_ORIGINS=https://stackfordge.vercel.app
   ```
5. Auto-deploys

### Option 3: Heroku

```bash
# Create app
heroku create stackfordge-api

# Add PostgreSQL
heroku addons:create heroku-postgresql:mini

# Set environment
heroku config:set CORS_ORIGINS=https://stackfordge.vercel.app

# Deploy
git push heroku main
```

## 🔧 Production Configuration

The app automatically uses production settings when `SPRING_PROFILES_ACTIVE=prod` or when `DATABASE_URL` is set.

### Required Environment Variables

```bash
DATABASE_URL=jdbc:postgresql://host:5432/stackfordge
CORS_ORIGINS=https://stackfordge.vercel.app
```

## 🔗 Connect to Frontend

Update your Next.js `.env.local`:

```
NEXT_PUBLIC_API_URL=https://your-backend-url.onrender.com
```

Push to GitHub → Vercel auto-redeploys → Full-stack app live! 🎉

## 📊 View Contact Submissions

### H2 Console (Development)
1. Go to http://localhost:8080/h2-console
2. Login with credentials above
3. Run: `SELECT * FROM contacts;`

### API (Production)
```bash
curl https://your-api-url.com/api/contacts
```

## 🧪 Testing

```bash
# Run tests
mvn test

# Build JAR
mvn clean package

# Run JAR
java -jar target/stackfordge-backend.jar
```

## 📁 Project Structure

```
stackfordge-backend/
├── src/
│   ├── main/
│   │   ├── java/com/stackfordge/
│   │   │   ├── StackFordgeApplication.java
│   │   │   ├── controller/ContactController.java
│   │   │   ├── service/ContactService.java
│   │   │   ├── model/Contact.java
│   │   │   ├── repository/ContactRepository.java
│   │   │   └── config/CorsConfig.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── application-prod.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🔐 Security Notes

- Input validation on all endpoints
- CORS restricted to your domains
- SQL injection protection via JPA
- TODO: Add Spring Security + JWT for admin endpoints

## 🛠 Tech Stack

- **Java 17**
- **Spring Boot 3.2.1**
- **Spring Data JPA**
- **H2** (dev) / **PostgreSQL** (prod)
- **Lombok**
- **Maven**

## 📝 Future Enhancements

- [ ] Email notifications on contact submission
- [ ] Rate limiting
- [ ] Admin authentication (JWT)
- [ ] Email service integration (SendGrid)
- [ ] Contact export to CSV
- [ ] Analytics dashboard

## 🐛 Troubleshooting

### Port already in use
```bash
# Check what's using port 8080
lsof -i :8080
# Kill it or change port in application.properties
```

### Database connection failed
```bash
# For H2, check console is enabled
# For PostgreSQL, verify DATABASE_URL is correct
```

### CORS errors
```bash
# Verify your frontend URL is in allowed origins
# Check application.properties or environment variables
```

## 📞 Support

- Check Spring Boot docs: https://spring.io/projects/spring-boot
- Check logs: `tail -f logs/stackfordge-backend.log`

---

**PRODUCTION READY** - Deploy now! 🚀

Built with Spring Boot 3.2.1 | Java 17 | PostgreSQL
