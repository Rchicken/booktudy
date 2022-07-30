FROM nginx

RUN openssl req -new -newkey rsa:2048 -nodes -x509 -days 365 \
            -keyout /etc/ssl/private/devcourse_R.key -out /etc/ssl/certs/devcourse_R.crt \
            -subj "/c=KR/ST=Seoul/L=GANGNAM/O=Programmers/OU=IT/CN=devcourse_R" && \
    chmod 600 /etc/ssl/private/devcourse_R.key && \
    chmod 600 /etc/ssl/certs/devcourse_R.crt

COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

EXPOSE 80 443

ENTRYPOINT ["nginx", "-g", "daemon off;"]
