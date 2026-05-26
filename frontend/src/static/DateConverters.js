export const localDateTimeArrayToDate = (array) => {
		if (!Array.isArray(array) || array.length < 5) {
			console.log("Invalid LocalDateTime value:", array);
    		return null;
		  }
          const [year, month, day, hour, minute, second = 0] = array;
		  return new Date(year, month - 1, day, hour, minute, second);
}

export const localDateArrayToDate = (array) => {
		if (!Array.isArray(array) || array.length < 3) {
			console.log("Invalid LocalDateTime value:", array);
    		return null;
		  }
          const [year, month, day = 0] = array;
		  return new Date(year, month - 1, day);
}