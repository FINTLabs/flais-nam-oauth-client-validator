# FLAIS NetIQ Access Manager OAuth Client Validator
[![Build and deploy](https://github.com/FINTLabs/flais-nam-oauth-client-validator/actions/workflows/cd.yaml/badge.svg)](https://github.com/FINTLabs/flais-nam-oauth-client-validator/actions/workflows/cd.yaml)

## Configuration

| Property           | Description | Default value |
|--------------------|:-----------:|--------------:|
| fint.ldap.url      |             |               |
| fint.ldap.user     |             |               |
| fint.ldap.password |             |               |

## Setup certificate

1. Get the certificate from the server

`echo "" | openssl s_client -connect localhost:6360 -showcerts 2>/dev/null | openssl x509 > server.cer`

2. Add the server certificate to a keystore (jks)

`keytool -import -file server.cer -alias namadmcon01 -keystore cacerts.jks`

3. Create a secret in Kubernetes

> This secret must be created directly in K8s and cannot go via 1Password :(

`kubectl create secret generic <name of secret> --from-literal=truststore_password=changeit --from-file=cacerts.jks`

