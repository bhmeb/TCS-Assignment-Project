package web.microservice.userLogin.xyz;

public class Employee { 

String Name;
String ID;
String Age;
public String getName() {
	return Name;
}
public void setName(String name) {
	Name = name;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public String getAge() {
	return Age;
}
public void setAge(String age) {
	Age = age;
}
public Employee(String name, String iD, String age) {
	super();
	Name = name;
	ID = iD;
	Age = age;
}
@Override
public String toString() {
	return "Employee [Name=" + Name + ", ID=" + ID + ", Age=" + Age + "]";
}





}
