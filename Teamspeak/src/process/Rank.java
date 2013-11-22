package process;

/**
 * A representation of modern ranks in WN.
 * @author Martin Borstad
 *
 */
public enum Rank {
	PRIVATE(19), CORPORAL(12), SERGEANT(13), STAFF_SERGEANT(14), MASTER_SERGEANT(15), SERGEANT_MAJOR(16);
	
	int rankId;
	private Rank(int i)
	{
		rankId = i;
	}
	
	static Rank RankConvert(String s)
	{
		switch(s.toLowerCase())
		{
		case "private" : return PRIVATE;
		case "corporal" : return CORPORAL;
		case "sergeant" : return SERGEANT;
		case "staff sergeant" : return STAFF_SERGEANT;
		case "master sergeant" : return MASTER_SERGEANT;
		case "sergeant major" : return SERGEANT_MAJOR;
		}
		return null;
	}
}
