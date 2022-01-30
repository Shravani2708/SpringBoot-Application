package com.springbootproject.coronovirusTracker.models;

public class LocationStats {

	
		private String state;
		private String country;
		private int latestTotalCases;
		private int DiffFromPrevDay;
		
		
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public int getLatestTotalCases() {
			return latestTotalCases;
		}
		public void setLatestTotalCases(int latestTotalCases) {
			this.latestTotalCases = latestTotalCases;
		}
		public int getDiffFromPrevDay() {
			return DiffFromPrevDay;
		}
		public void setDiffFromPrevDay(int DiffFromPrevDay) {
			this.DiffFromPrevDay = DiffFromPrevDay;
		}
		@Override
		public String toString() {
			return "LocationStats [state=" + state + ", country=" + country + ", latestTotalCases=" + latestTotalCases
					+ ", DiffFromPrevDay=" + DiffFromPrevDay + "]";
		}
		
		
		
		


}
