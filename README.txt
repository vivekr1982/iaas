How To Run "iaas configurer":
     Run "VMServiceTest", it has two methods. One runs with just one Datacenter object.
	 The second test class actually shows the fine grained locking mechanism.
	 
	 In the example below (TestResults) there are four concurrent requests for creating two Datacenter, one server and one storage.
	 When the first request executes, it locks the datacenter with ItemId 1, which will subsequently lock the server and in turn will lock the storage.
	 The server requests executes in parallel. So there is server request with ItemId 2 is already in execution, the datacenter server dependency will wait until this gets released.
	 Until everything is unlocked the second datacenter request thread is on wait state.


Files Includes With This Project:
    
	Model classes
		Action.java
		Datacenter.java
		Item.java
		ItemType.java
		Request.java
		Server.java
		Storage.java
	
	Service class
		VMService.java
	
	Processor class	
		MockedBackendProcessor.java
	
	Static data
		DataSet.java
		
	Test Class
		VMServiceTest.java



Design Decisions:
     I have implemented the coding challenge by implementing the fine grained locking mechanism using Java's Reenterent locks.
	 
	 Model design 
			Item.java is the abstract base class, the three Model objects Datacenter, Server, Storage extend the abstract base class.
			Each of these Model objects have a list of dependencies, Datacenter has list of Servers which in turn has list of Storages.

			To mock this data, i have created DataSet.java which has the static map of each of this Model objects.

Test Results:
	For the 4 parallel requests
	
	Runnable w1 = new VMService(request1, new MockedBackendProcessor());
    Runnable w2 = new VMService(request2, new MockedBackendProcessor());
    Runnable w3 = new VMService(request3, new MockedBackendProcessor());
    Runnable w4 = new VMService(request1, new MockedBackendProcessor());
	
	TEST RESULT:
	
	Jan 28, 2019 9:03:58 AM com.ionos.iaas.service.VMService processServer
	INFO: 2 - Server ID: 2 Locked
	Jan 28, 2019 9:03:58 AM com.ionos.iaas.service.VMService processDatacenter
	INFO: 1 - Datacenter ID: 1 Locked
	Jan 28, 2019 9:03:58 AM com.ionos.iaas.service.VMService processStorage
	INFO: 5 - Storage ID: 5 Locked
	Jan 28, 2019 9:03:58 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 1 Locked
	Jan 28, 2019 9:03:58 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 1 Locked
	Jan 28, 2019 9:04:00 AM com.ionos.iaas.service.VMService processServer
	INFO: 2 - Server ID: 2 Unlocked
	Jan 28, 2019 9:04:00 AM com.ionos.iaas.service.VMService processServer
	INFO: 5 - Server ID: 2 Locked
	Jan 28, 2019 9:04:00 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 2 Locked
	Jan 28, 2019 9:04:01 AM com.ionos.iaas.service.VMService processStorage
	INFO: 5 - Storage ID: 5 Unlocked
	Jan 28, 2019 9:04:01 AM com.ionos.iaas.service.VMService processStorage
	INFO: 5 - Server ID: 2 Unlocked
	Jan 28, 2019 9:04:01 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 3 Locked
	Jan 28, 2019 9:04:04 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 2 Locked
	Jan 28, 2019 9:04:04 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 4 Locked
	Jan 28, 2019 9:04:06 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 5 Locked
	Jan 28, 2019 9:04:07 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 6 Locked
	Jan 28, 2019 9:04:10 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 3 Locked
	Jan 28, 2019 9:04:10 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 7 Locked
	Jan 28, 2019 9:04:12 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 8 Locked
	Jan 28, 2019 9:04:13 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 9 Locked
	Jan 28, 2019 9:04:16 AM com.ionos.iaas.service.VMService processDatacenter
	INFO: 1 - Datacenter ID: 1 Unlocked Everything
	Jan 28, 2019 9:04:16 AM com.ionos.iaas.service.VMService processDatacenter
	INFO: 1 - Datacenter ID: 1 Locked
	Jan 28, 2019 9:04:16 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 1 Locked
	Jan 28, 2019 9:04:16 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 1 Locked
	Jan 28, 2019 9:04:18 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 2 Locked
	Jan 28, 2019 9:04:19 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 3 Locked
	Jan 28, 2019 9:04:22 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 2 Locked
	Jan 28, 2019 9:04:22 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 4 Locked
	Jan 28, 2019 9:04:24 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 5 Locked
	Jan 28, 2019 9:04:25 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 6 Locked
	Jan 28, 2019 9:04:28 AM com.ionos.iaas.service.VMService processServer
	INFO: 1 - Server ID: 3 Locked
	Jan 28, 2019 9:04:28 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 7 Locked
	Jan 28, 2019 9:04:30 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 8 Locked
	Jan 28, 2019 9:04:31 AM com.ionos.iaas.service.VMService processStorage
	INFO: 1 - Storage ID: 9 Locked
	Jan 28, 2019 9:04:34 AM com.ionos.iaas.service.VMService processDatacenter
	INFO: 1 - Datacenter ID: 1 Unlocked Everything
			
			
Profiling Results:
	Attached the screenshot VMServiceTestPerf.png
