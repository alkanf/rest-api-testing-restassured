package day9;

public class POJO {
//variable, only body variables. Not dynamic variables like "id"
String name;
String location;
String phone;
String courses[];
	
// defaut constructor
public POJO () {}

public POJO(String name, String location, String phone, String[] courses) 
{
this.name = name;
this.location = location;
this.phone = phone;
this.courses = courses;
}

//actions setters & getters

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getLocation() {
	return location;
}

public void setLocation(String location) {
	this.location = location;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String[] getCourses() {
	return courses;
}

public void setCourses(String[] courses) {
	this.courses = courses;
}
//toString(), all classes have toString method as default
//You can return values individually but we have array so use StringBuilder
@Override
public String toString() { 
StringBuilder sb = new StringBuilder();
sb.append(name).append(" ").append(location).append(" ").append(phone).append(" ");
if(courses!=null && courses.length>0) {
	for(String course : courses) {
		sb.append(course).append(" ");
	}
}
return sb.toString();
}

}
