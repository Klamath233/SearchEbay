#! /bin/bash
## This script enable more seamless build and deployment.
## @file build.sh
## @author Xi Han

ant clean
ant build
sudo rm -rf /var/lib/tomcat7/webapps/eBay
sudo rm -rf /var/lib/tomcat7/work/Catalina/localhost/eBay/
ant deploy

