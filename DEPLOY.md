# StackFordge Backend Deployment Guide

## 🚀 Deploy to Render.com (FREE)

### Step 1: Prepare

1. Push this code to GitHub
2. Go to [render.com](https://render.com) and sign up

### Step 2: Create Web Service

1. Click "New +" → "Web Service"
2. Connect your GitHub repository
3. Name: `stackfordge-api`
4. Branch: `main`
5. Root Directory: Leave empty (if backend is in root) OR specify path
6. Environment: `Java`
7. Build Command: `mvn clean package`
8. Start Command: `java -jar target/stackfordge-backend.jar`

### Step 3: Add PostgreSQL

1. In same Render dashboard, click "New +" → "PostgreSQL"
2. Name: `stackfordge-db`
3. Free tier
4. Create Database

### Step 4: Connect Database

1. Go back to your Web Service
2. Environment → Add Environment Variable
3. Click "Add from Database" → Select your PostgreSQL
4. It will auto-add `DATABASE_URL`

### Step 5: Add CORS

Add environment variable:
- Key: `CORS_ORIGINS`
- Value: `https://stackfordge.vercel.app`

### Step 6: Deploy

Click "Create Web Service" → Wait 5-10 minutes

Your API will be live at: `https://stackfordge-api.onrender.com`

## 🔗 Connect to Frontend

1. In your Next.js project, create `.env.local`:
   ```
   NEXT_PUBLIC_API_URL=https://stackfordge-api.onrender.com
   ```

2. Push to GitHub

3. Vercel auto-redeploys with new API URL

4. Test contact form on https://stackfordge.vercel.app

## ✅ Verify Deployment

```bash
# Health check
curl https://stackfordge-api.onrender.com/api/contacts/health

# Should return: {"status":"UP","service":"StackFordge API"}
```

## 🎉 Done!

Your full-stack StackFordge application is now live!

- Frontend: https://stackfordge.vercel.app
- Backend: https://stackfordge-api.onrender.com

---

**Note**: Free tier may sleep after inactivity. First request after sleep takes ~30 seconds.
