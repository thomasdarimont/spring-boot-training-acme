package acme.mgmt.features;

public enum Feature {

	ACME_FEATURE_1, 
	
	ACME_FEATURE_2,
	
	ACME_TODO_FIND_ALL,
	
	ACME_TODO_FIND_BY_TITLE,
	
	ACME_TODO_NEW
	;
	
	public void record(FeatureUsageMonitor monitor) {
		monitor.record(this);
	}
}