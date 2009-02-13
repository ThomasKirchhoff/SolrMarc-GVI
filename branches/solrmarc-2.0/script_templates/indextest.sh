#! /bin/bash
# indextest.sh
# Diagnostic program to show how a set of marc records would be indexed,
# without actually adding any records to Solr.
# $Id: indextest.sh 

E_BADARGS=65

if [ $# -eq 0 ]
then
  echo "    Usage: `basename $0` [config.properties] ./path/to/marc.mrc "
  echo "      Note that if the config.properties file is not specified the Jarfile will be searched for"
  echo "      a file whose name ends with \"config.properties\""
  exit $E_BADARGS
fi

java @MEM_ARGS@ -Done-jar.main.class="org.solrmarc.marc.MarcPrinter" -jar @CUSTOM_JAR_NAME@ $1 $2 index

exit 0
