#!/bin/bash
# abort bash on error
set -e

if [ -z "$ROOT_ORG_IATRIX" ]
then
  export ROOT_ORG_IATRIX=/home/jenkins/downloads/org.iatrix.v2
fi

if [ ! -d "$ROOT_ORG_IATRIX" ]
then
  echo "ROOT_ORG_IATRIX (actually defined as $ROOT_ORG_IATRIX) must exist"
  exit 1
fi

# set some default values
export parent=`dirname $0`
if [ -z "$VARIANT" ]
then
  export VARIANT=snapshot
fi
if [ -z "$path_to_eclipse_4_2" ]
then
  export path_to_eclipse_4_2=/home/srv/p2Helpers/eclipse/eclipse
fi

rm -rf ${ROOT_ORG_IATRIX}/$VARIANT
cp -rpu *p2site/target/repository/ ${ROOT_ORG_IATRIX}/$VARIANT
export title="Elexis-Application P2-repository ($VARIANT)"
echo "Creating repository $ROOT_ORG_IATRIX/$VARIANT/index.html"
tee  ${ROOT_ORG_IATRIX}/$VARIANT/index.html <<EOF
<?xml version="1.0" encoding="UTF-8"?>
<html>
  <head><title>$title</title></head>
  <body>
    <h1>$title</h1>
    <ul>
      <li><a href="binary">binary</a></li>
      <li><a href="plugins">plugins</a></li>
      <li><a href="features">features</a></li>
    </ul>
    </p>
    <p>Installed `date`
    </p>
  </body>
</html>
EOF

