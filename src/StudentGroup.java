import java.util.Date;
import java.util.*;
/**
 * A fix-sized array of students
 * array length should always be equal to the number of stored elements
 * after the element was removed the size of the array should be equal to the number of stored elements
 * after the element was added the size of the array should be equal to the number of stored elements
 * null elements are not allowed to be stored in the array
 * 
 * You may add new methods, fields to this class, but DO NOT RENAME any given class, interface or method
 * DO NOT PUT any classes into packages
 *
 */
public class StudentGroup implements StudentArrayOperation {

	private Student[] students;
	
	/**
	 * DO NOT remove or change this constructor, it will be used during task check
	 * @param length
	 */
	public StudentGroup(int length) {
		this.students = new Student[length];
	}

	@Override
	public Student[] getStudents() {
		return this.students;
		// Add your implementation here
		
	}

	@Override
	public void setStudents(Student[] students) throws IllegalArgumentException {
		if(students == null) 
			throw new IllegalArgumentException();
		else
			this.students = students;
		
	}

	@Override
	public Student getStudent(int index) throws IllegalArgumentException {
		// Add your implementation here
		if(index < 0 || index > students.length)
			throw new IllegalArgumentException();
		
		return students[index];
	}

	@Override
	public void setStudent(Student student, int index) throws IllegalArgumentException {
		if(student == null || index < 0 || index > students.length)
			throw new IllegalArgumentException();
		else 
			students[index] = student;
		// Add your implementation here
	}

	@Override
	public void addFirst(Student student) throws IllegalArgumentException {
		if(student == null)
			throw new IllegalArgumentException();
		int n = (this.students == null) ? 0 : this.students.length;
		Student[] students = new Student[n+1];
		students[0] = student;
		for(int i = 1; i <=  n; i++){
			students[i] = this.students[i-1];
		}
		this.students = students;
		// Add your implementation here
	}

	@Override
	public void addLast(Student student) {
		if(student == null)
			throw new IllegalArgumentException();
		int n = (this.students == null) ? 0 : this.students.length;
		Student[] students = new Student[n+1];
		for(int i = 0; i <  n; i++){
			students[i] = this.students[i];
		}
		students[n] = student;
		this.students = students;
		
		
		
		// Add your implementation here
	}

	@Override
	public void add(Student student, int index) throws IllegalArgumentException{
		if(student == null)
			throw new IllegalArgumentException();
		
		int n = (this.students == null) ? 0 : this.students.length;
		if(index < 0 || index > n)
			throw new IllegalArgumentException();
		
		Student[] students = new Student[n+1];
		for(int i = 0, j = 0; i <=  n; i++, j++){
			if(i == index){
				j--;
				students[i] = student;
			}
			else
				students[i] = this.students[j];
		}
		this.students = students;
	}

	@Override
	public void remove(int index) throws IllegalArgumentException{
		if(index < 0 || index > students.length)
			throw new IllegalArgumentException();
		int n = (this.students == null) ? 0 : this.students.length;
		Student[] students = new Student[n-1];
		for(int i = 0, k = 0; k <  n ; i++, k++){
			if(k == index){
				i--;
				continue;
			}
			else
				students[i] = this.students[k];
		}
		this.students = students;
		
		// Add your implementation here
	}

	private int getStudentIndex(Student student){
		int index = -1;
		for(int i = 0; i < this.students.length; i++)
		{
			if(this.students[i].equals(student)){
				index = i;
				break;
			}
		}
		return index;
	}
	
	@Override
	public void remove(Student student) throws IllegalArgumentException {
		if(student == null)
			throw new IllegalArgumentException();
		int removableIndex = getStudentIndex(student);
		if(removableIndex == -1)
			throw new IllegalArgumentException();
		else
			remove(removableIndex);
		// Add your implementation here
	}

	@Override
	public void removeFromIndex(int index) throws IllegalArgumentException{
		if(index < 0 || index > this.students.length)
			throw new IllegalArgumentException();
		Student[] students = new Student[index];
		for(int i = 0; i <= index; i++){
			students[i] = this.students[i];
		}
		this.students = students;
		// Add your implementation here
	}

	@Override
	public void removeFromElement(Student student) throws IllegalArgumentException {
		if(student == null)
			throw new IllegalArgumentException();
		int removableIndex = getStudentIndex(student);
		if(removableIndex == -1)
			throw new IllegalArgumentException();
		else
			removeFromIndex(removableIndex);
		// Add your implementation here
	}

	@Override
	public void removeToIndex(int index) {
		if(index < 0 || index > this.students.length)
			throw new IllegalArgumentException();
		int n = this.students.length - index - 1;
		Student[] students = new Student[n];
		for(int i = 0, j = index + 1; i < n; i++, j++ ){
			students[i] = this.students[j];
		}
		this.students = students;
		// Add your implementation here
	}

	@Override
	public void removeToElement(Student student) {
		if(student == null)
			throw new IllegalArgumentException();
		int removableIndex = getStudentIndex(student);
		if(removableIndex == -1)
			throw new IllegalArgumentException();
		else
			removeToIndex(removableIndex);
		// Add your implementation here
	}

	@Override
	public void bubbleSort() {
		int n = students.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (students[j].getId() > students[j+1].getId())
                {
                    Student temp = students[j];
                    students[j] = students[j+1];
                    students[j+1] = temp;
                }
		
		// Add your implementation here
	}

	@Override
	public Student[] getByBirthDate(Date date) {
		if(date == null)
			throw new IllegalArgumentException();
		ArrayList<Student> studentsBeforeDate = new ArrayList<Student>();
		for(int i = 0; i< this.students.length; i++){
			if(this.students[i].getBirthDate().before(date) ||  this.students[i].getBirthDate().toString().equals(date.toString()))
				studentsBeforeDate.add(this.students[i]);
		}
		// Add your implementation here
		return studentsBeforeDate.toArray(new Student[0]);
	}

	@Override
	public Student[] getBetweenBirthDates(Date firstDate, Date lastDate) {
		if(firstDate == null || lastDate == null)
			throw new IllegalArgumentException();
		ArrayList<Student> studentsBeforeDate = new ArrayList<Student>();
		for(int i = 0; i< this.students.length; i++){
			Date currentStudentDate = this.students[i].getBirthDate();
			if(
				(currentStudentDate.after(firstDate) && currentStudentDate.after(lastDate))
				|| currentStudentDate.toString().equals(firstDate.toString())
				|| currentStudentDate.toString().equals(lastDate.toString()) 
			  ) 	
				studentsBeforeDate.add(this.students[i]);
		}
		// Add your implementation here
		return studentsBeforeDate.toArray(new Student[0]);
	}

	
	@Override
	public Student[] getNearBirthDate(Date date, int days) {
		// Add your implementation here
		for(int i = 0; i < this.students.length ; i++){
			Date currentStudentDate = this.students[i].getBirthDate();
			
		}
		return null;
	}

	@Override
	public int getCurrentAgeByDate(int indexOfStudent) throws IllegalArgumentException {
		Date current = new Date();
		if(indexOfStudent < 0 || indexOfStudent >= this.students.length)
			throw new IllegalArgumentException();
		Date studentDate = this.students[indexOfStudent].getBirthDate();
		int age = current.getYear() - studentDate.getYear();
		
		studentDate.setYear(current.getYear());
		if(studentDate.before(current))
			age--;
		
		// Add your implementation here
		return age;
	}

	@Override
	public Student[] getStudentsByAge(int age) {
		ArrayList<Student> studentsOfAge = new ArrayList<Student>();
		for(int i = 0; i< this.students.length; i++){
			if( (getCurrentAgeByDate(i)) == age)
				studentsOfAge.add(this.students[i]);
		}
		// Add your implementation here
		return studentsOfAge.toArray(new Student[0]);
	}

	@Override
	public Student[] getStudentsWithMaxAvgMark() {
		double maxmark = 0;
		for(int i = 0; i< this.students.length; i++){
			if(this.students[i].getAvgMark() > maxmark)
					maxmark = this.students[i].getAvgMark();
			}
		
		ArrayList<Student> studentsWithMaxMark = new ArrayList<Student>();
		for(int i = 0; i< this.students.length; i++){
			if(this.students[i].getAvgMark() == maxmark)
				studentsWithMaxMark.add(this.students[i]);
		}
		// Add your implementation here
		return studentsWithMaxMark.toArray(new Student[0]);
		
	}

	@Override
	public Student getNextStudent(Student student)  throws IllegalArgumentException {
		// Add your implementation here
		if(student == null)
			throw new IllegalArgumentException();
		int indexOfStudent = getStudentIndex(student);
		if(indexOfStudent == this.students.length - 1) 
			indexOfStudent = -1;
		return this.students[ indexOfStudent + 1 ] ;
	}
}
