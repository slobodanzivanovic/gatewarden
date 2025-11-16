IMAGE_NAME_LOCAL ?= docker.delveye.com/gatewarden:local

.PHONY: generate-local-quarkus-image
generate-local-quarkus-image:
	@echo "Building local Quarkus image: $(IMAGE_NAME_LOCAL)"
	./mvnw clean package -DskipTests -Dquarkus.profile=local

	docker build -f Dockerfile -t $(IMAGE_NAME_LOCAL) .
