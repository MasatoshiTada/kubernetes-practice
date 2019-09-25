#!/bin/bash
echo 'Creating Cluster Role Binding...'
kubectl create clusterrolebinding cluster-admin-binding --clusterrole cluster-admin --user $(gcloud config get-value account)
echo 'Applying mandatory.yaml...'
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/mandatory.yaml
echo 'Applying cloud-generic.yaml...'
kubectl apply -f https://raw.githubusercontent.com/kubernetes/ingress-nginx/master/deploy/static/provider/cloud-generic.yaml
echo 'Finished creating Nginx Ingress Controller.'