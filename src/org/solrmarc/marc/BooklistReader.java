package org.solrmarc.marc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class BooklistReader extends SolrReIndexer
{

    public BooklistReader(String properties, String[] args) throws IOException
    {
        super(properties, args);
//        Properties props = new Properties();
//        InputStream in = new FileInputStream(properties);
//        // load the properties
//        props.load(in);
//        in.close();
//        
//        solrCoreDir = getProperty(props, "solr.path");
//        solrDataDir = getProperty(props, "solr.data.dir");
//        if (solrDataDir == null) solrDataDir = solrCoreDir + "/data";
//        // Set up Solr core
//        try{
//            System.setProperty("solr.data.dir", solrDataDir);
//            solrConfig = new SolrConfig(solrCoreDir, "solrconfig.xml", null);
//            solrCore = new SolrCore("Solr", solrDataDir, solrConfig, null);
//        }
//        catch (Exception e)
//        {
//            System.err.println("Couldn't set the instance directory");
//            e.printStackTrace();
//            System.exit(1);
//        }
//        verbose = Boolean.parseBoolean(getProperty(props, "marc.verbose"));
//        
//        updateHandler = solrCore.getUpdateHandler();
//        String indexerName = getProperty(props, "solr.indexer");
//        String indexerProps = getProperty(props, "solr.indexer.properties");
//        
//        try
//        {
//            Class indexerClass;
//            try {
//                indexerClass = Class.forName(indexerName);
//            }
//            catch (ClassNotFoundException e)
//            {
//                Class baseIndexerClass = SolrIndexer.class;
//                String baseName = baseIndexerClass.getPackage().getName();
//                String fullName = baseName + "." + indexerName;
//                indexerClass = Class.forName(fullName);
//            }
//            Constructor constructor = indexerClass.getConstructor(new Class[]{String.class});
//            Object instance = constructor.newInstance(indexerProps);
//            if (instance instanceof SolrIndexer)
//            {
//                indexer = (SolrIndexer)instance;
//            }
//            else
//            {
//                System.err.println("Error: Custom Indexer "+ indexerName +" must be subclass of SolrIndexer .  Exiting...");
//                System.exit(1);
//            }
//        }
//        catch (Exception e)
//        {
//            if (e instanceof ParseException)
//            {
//                System.err.println("Error configuring Indexer from properties file.  Exiting...");
//                System.exit(1);
//            }            
//            System.err.println("Unable to find Custom indexer: "+ indexerName);
//            System.err.println("Using default SolrIndexer with properties file: " + indexerProps);
//            try {
//                indexer = new SolrIndexer(indexerProps);
//            }
//            catch (Exception e1)
//            {
//                System.err.println("Error configuring Indexer from properties file.  Exiting...");
//                System.exit(1);
//            }
//        }
//        
    }

//    // Check first for a particular property in the System Properties, so that the -Dprop="value" command line arg 
//    // mechanism can be used to override values defined in the passed in property file.  This is especially useful
//    // for defining the marc.source property to define which file to operate on, in a shell script loop.
//    private String getProperty(Properties props, String propname)
//    {
//        String prop;
//        if ((prop = System.getProperty(propname)) != null)
//        {
//            return(prop);
//        }
//        if ((prop = props.getProperty(propname)) != null)
//        {
//            return(prop);
//        }
//        return null;
//    }
    
    public void readBooklist(String filename)
    {
//        AnselToUnicode conv = new AnselToUnicode();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
            String line;
            while ((line = reader.readLine()) != null)
            {
                String fields[] = line.split("\\|");
 //               map.put(fields[fields.length-2], fields.clone());
                Map<String, String> valuesToAdd = new LinkedHashMap<String, String>();
                valuesToAdd.put("fund_code_facet", fields[11]);
                valuesToAdd.put("date_received_facet", fields[0]);
                readAndUpdate("id", "u"+fields[9], valuesToAdd);
            }
        }
        catch (FileNotFoundException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
//    private Record lookup(String doc_id)
//    {
//        RefCounted<SolrIndexSearcher> rs = solrCore.getSearcher();
//        SolrIndexSearcher s = rs.get();
//        Term t = new Term("id", doc_id);
//        int docNo;
//        String marcRecord = null;
//        try
//        {
//            docNo = s.getFirstMatch(t);
//            if (docNo > 0)
//            {
//                Document doc = s.doc(docNo);
//                Field field = doc.getField("marc_display");
//                marcRecord = field.stringValue();
//            }
//            else
//            {
//                URL url = new URL("http://solrpowr.lib.virginia.edu:8080/solr/select/?q=id%3A"+doc_id+"&start=0&rows=1");
//                InputStream stream = url.openStream();
//                //The evaluate methods in the XPath and XPathExpression interfaces are used to parse an XML document with XPath expressions. The XPathFactory class is used to create an XPath object. Create an XPathFactory object with the static newInstance method of the XPathFactory class.
//
//                XPathFactory  factory = XPathFactory.newInstance();
//
//                // Create an XPath object from the XPathFactory object with the newXPath method.
//
//                XPath xPath = factory.newXPath();
//
//                // Create and compile an XPath expression with the compile method of the XPath object. As an example, select the title of the article with its date attribute set to January-2004. An attribute in an XPath expression is specified with an @ symbol. For further reference on XPath expressions, see the XPath specification for examples on creating an XPath expression.
//
//                XPathExpression  xPathExpression=
//                    xPath.compile("/response/result/doc/arr[@name='marc_display']/str");
//                
//                InputSource inputSource = new InputSource(stream);
//                marcRecord = xPathExpression.evaluate(inputSource);
//            }
//            
//            MarcXmlReader reader = new MarcXmlReader(new ByteArrayInputStream(marcRecord.getBytes("UTF8")));
//            if (reader.hasNext())
//            {
//                Record rec = reader.next();
//                return(rec);
//            }
//
//        }
//        catch (IOException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        catch (XPathExpressionException e)
//        {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return(null);
//    }

//    public void update(Map<String, Object> map)
//    { 
//        AddUpdateCommand addcmd = new AddUpdateCommand();
//        DocumentBuilder builder = new DocumentBuilder(solrCore.getSchema());
//        builder.startDoc();
//        Iterator<String> keys = map.keySet().iterator();
//        while (keys.hasNext())
//        {
//            String key = keys.next();
//            Object value = map.get(key);
//            if (value instanceof String)
//            {
//                builder.addField(key, (String)value);
//            }
//            else if (value instanceof Collection)
//            {
//                Iterator<String> valIter = ((Collection)value).iterator();
//                while (valIter.hasNext())
//                {
//                    String collVal = valIter.next();
//                    builder.addField(key, collVal);
//                }
//            }
//        }
//        builder.endDoc();
//        
//        // finish up
//        addcmd.doc = builder.getDoc();
//        
//        if (verbose)
//        {
////            System.out.println(record.toString());
//            String doc = addcmd.doc.toString().replaceAll("> ", "> \n");
//            System.out.println(doc);
//        }
//        addcmd.allowDups = false;
//        addcmd.overwriteCommitted = true;
//        addcmd.overwritePending = true;
//       
//        try {
//            updateHandler.addDoc(addcmd);
//        } 
//        catch (IOException e) 
//        {
//            System.err.println("Couldn't add document");
//            e.printStackTrace();
//        }                
//    }
//    
//    public void finish()
//    {
//        try {
//            System.out.println("Calling commit");
//            commit(false);
//        } 
//        catch (IOException e) {
//            System.err.println("Final commit and optmization failed");
//            e.printStackTrace();
//        }
//        
//        System.out.println("Done with commit, closing Solr");       
//        solrCore.close();
//    }
//
//
//    /**
//     * Commit the document to the repository and optimize the index
//     * @param optimize
//     * @throws IOException
//     */
//    public void commit(boolean optimize) throws IOException 
//    {
//        CommitUpdateCommand commitcmd = new CommitUpdateCommand(optimize);
//        updateHandler.commit(commitcmd);
//    }
    /**
     * @param args
     */
    public static void main(String[] args)
    {
        String properties = "import.properties";
        if(args.length > 0 && args[0].endsWith(".properties"))
        {
            properties = args[0];
            String newArgs[] = new String[args.length-1];
            System.arraycopy(args, 1, newArgs, 0, args.length-1);
            args = newArgs;
        }
        System.out.println("Loading properties from " + properties);
        
        BooklistReader reader = null;
        try
        {
            reader = new BooklistReader(properties, args);
        }
        catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        reader.readBooklist("booklists.txt");
        
        reader.finish();

    }

}
