# FLAIS NetIQ Access Manager OAuth Client Validator

[![Build and deploy](https://github.com/FINTLabs/flais-nam-oauth-client-validator/actions/workflows/cd.yaml/badge.svg)](https://github.com/FINTLabs/flais-nam-oauth-client-validator/actions/workflows/cd.yaml)

## Configuration

| Property             | Description                                                         | Default value |
|----------------------|---------------------------------------------------------------------|---------------|
| spring.ldap.urls     | Url of the LDAP server. E.g. ldap://localhost:389                   |               |
| spring.ldap.username | DN of user with priviliges to read and delete users in the base OU. |               |
| spring.ldap.password | Password for the user.                                              |               |

## Setup certificate
Both for local development and for running in the cluster we need the SSL cert of the admin console. To download this
cert we first need to connect to the VPN and then create an ssh tunnel 
e.g. `ssh -L 6360:<IP to admin console>:636 <username>@<jump server>`

1. Get the certificate from the server

`echo "" | openssl s_client -connect localhost:6360 -showcerts 2>/dev/null | openssl x509 > server.cer`

2. Add the server certificate to a keystore (jks)

`keytool -import -file server.cer -alias namadmcon01 -keystore cacerts.jks`

3. Create a secret in Kubernetes

> This secret must be created directly in K8s and cannot go via 1Password :(

`kubectl create secret generic <name of secret> --from-literal=truststore_password=changeit --from-file=cacerts.jks`

