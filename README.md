# Weblogic 12.2.1.2 thin 3 client

Relevant oracle documentation:
http://docs.oracle.com/middleware/12212/wls/SACLT/basics.htm#SACLT117

The point of this repository is to evaluate weblogic thin client class not found errors.

Namely:
'''
Caused by: javax.naming.NameNotFoundException:
 While trying to lookup 'java:global.weblogic-thin-t3.LogMessageOnServerLogImpl!remoteejb.LogMessageOnServerLog' 
 didn't find subcontext 'weblogic-thin-t3'. Resolved 'java:global'
	at weblogic.jndi.internal.BasicNamingNode.newNameNotFoundException(BasicNamingNode.java:1292)
	at weblogic.jndi.internal.BasicNamingNode.lookupHere(BasicNamingNode.java:349)
	at weblogic.jndi.internal.ServerNamingNode.lookupHere(ServerNamingNode.java:222)
	at weblogic.jndi.internal.BasicNamingNode.lookup(BasicNamingNode.java:214)
	at weblogic.jndi.internal.ServerNamingNode.lookup(ServerNamingNode.java:521)
	at weblogic.jndi.internal.BasicNamingNode.lookup(BasicNamingNode.java:228)
	at weblogic.jndi.internal.ServerNamingNode.lookup(ServerNamingNode.java:521)
	at weblogic.jndi.internal.RootNamingNode.lookup(RootNamingNode.java:84)
	at weblogic.jndi.internal.RootNamingNode_WLSkel.invoke(Unknown Source)
	at weblogic.rmi.internal.BasicServerRef.invoke(BasicServerRef.java:645)
	at weblogic.rmi.cluster.ClusterableServerRef.invoke(ClusterableServerRef.java:246)
	at weblogic.rmi.internal.BasicServerRef$2.run(BasicServerRef.java:534)
	at weblogic.security.acl.internal.AuthenticatedSubject.doAs(AuthenticatedSubject.java:368)
	at weblogic.security.service.SecurityManager.runAs(SecurityManager.java:163)
'''

obtained while using the thin client, but that were not obtained while using the full client.
The sample application fails to reproduce the issue....
In this case remote communication is successful, contraty to original expectations building the sample app.


The Sample project generated using the JEE7 archetype:
'''
mvn -DarchetypeGroupId=org.codehaus.mojo.archetypes \
    -DarchetypeArtifactId=webapp-javaee7 \
    -DarchetypeVersion=1.1 \
    -DgroupId=SampleApp \
    -DartifactId=weblogic-thin-t3 \
    -Dversion=1.0.0-SANPSHOT \
    -Dpackage=sample.app \
    -Darchetype.interactive=false \
    --batch-mode \
    archetype:generate
''''