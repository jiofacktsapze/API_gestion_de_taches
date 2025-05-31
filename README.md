# API_gestion_de_taches
Cette API permet aux utilisateurs de gérer une liste de tâches : création, lecture, mise à jour et suppression de tâches. Elle est développée avec Spring Boot 3.2.6 et documentée grâce à Swagger/OpenAPI.

✅ Prérequis • IDE recommandé : IntelliJ IDEA • Outils de test : Swagger UI ou Postman (optionnel) • Environnement : JDK 21 et Maven • PostgreSQL • Docker (pour tester avec une BDD Dockerisée)

🚀 Lancement du projet 1. Cloner le dépôt : git clone https://github.com/jiofacktsapze/API_gestion_de_taches 2. Configurer la base de données dans application.properties : Exemple : spring.datasource.url=jdbc:postgresql://localhost:5441/todo-app spring.datasource.username=postgres spring.datasource.password=motdepasse spring.jpa.hibernate.ddl-auto=update 3. Lancer l’application : ./mvnw spring-boot:run L’application sera disponible à l’adresse : ➤ http://localhost:8080

📚 Documentation Swagger Une fois l’application démarrée, la documentation Swagger est disponible à : 👉 http://localhost:8080/swagger-ui.html Ou (si redirigé automatiquement) : 👉 http://localhost:8080/swagger-ui/index.html 📦 Points de terminaison disponibles ✅ Créer un produit

POST /api/products Exemple de corps JSON : { "name": "Savon liquide", "price": 1500, "quantity": 10 }

📋 Lire toutes les tâches et filtrer par status

GET /api/tasks

🔄 Mettre à jour une tâche

PUT /api/tasks/{id} 

🗑️ Supprimer une tâche 

DELETE /api/tasks/{id}

📋 Créer une nouvelle tâche

POST /api/tasks

🔄 Changer le status d'une tâche

PATCH /api/tasks/{id}/status

⚠️ Validation 
• Toute nouvelle tâche qui est créée a pour statut par défaut TO_DO. • Transitions autorisées: TO_DO -> IN_PROGRESS; IN_PROGRESS -> DONE.

💡 Conseils de test

Utilise Swagger UI pour tester facilement toutes les routes.
Utilise Postman ou Curl pour tests avancés.
Vérifie les logs en cas d’erreur (target/classes/logs ou console).
