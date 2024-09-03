package com.ems.backend.model.dto.response;

import java.util.ArrayList;

import com.ems.backend.model.entity.Department;
import com.ems.backend.model.entity.Department_;
import com.ems.backend.model.entity.Employee;
import com.ems.backend.model.entity.Employee_;
import com.ems.backend.model.entity.MyUserAccount;
import com.ems.backend.model.entity.MyUserAccount_;
import com.ems.backend.model.entity.Position;
import com.ems.backend.model.entity.Position_;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DynamicSearchDto {

	private int id;
	private String departmentName;
	private String username;
	private String salary;
	private String image;
	private String dob;
	private String passport;
	private String startDate;
	private String address;
	private String email;
	private String positionName;
	private String active;
	private String role;
	
	
//	public static EntityManager em;
	public static Join<Employee, Department> department;
	public static Join<Employee, Position> position;
	public static Join<Employee, MyUserAccount> myUserAccount;
	
	public static void joinTable(Root<Employee> root) {
		
		
		department = root.join(Employee_.department);
		position = root.join(Employee_.position_id);
		myUserAccount = root.join(Employee_.myUserAccount);
	}
	
	public static void projection(CriteriaQuery<DynamicSearchDto> cq, CriteriaBuilder cb, Root<Employee> root) {
		
//		CriteriaBuilder cb = em.getCriteriaBuilder();
		cq.select(cb.construct(
				  DynamicSearchDto.class,
				  root.get(Employee_.id),
				  department.get(Department_.name),
				  root.get(Employee_.username),
				  root.get(Employee_.salary).as(String.class),
				  root.get(Employee_.image),
				  root.get(Employee_.dob).as(String.class),
				  root.get(Employee_.passport),
				  root.get(Employee_.startDate).as(String.class),
				  root.get(Employee_.address),
				  myUserAccount.get(MyUserAccount_.email),
				  position.get(Position_.name),
				  myUserAccount.get(MyUserAccount_.active),
				  myUserAccount.get(MyUserAccount_.role)
				            ));
	}
	

	public static Predicate[] predicates(CriteriaBuilder cb,String keyword,Root<Employee> root) {
		
		var predicateList = new ArrayList<Predicate>();
		
		String likePattern = "%".concat(keyword).concat("%");
		
		if(null != keyword && !keyword.isEmpty()) { 
			
			try {
				if(Integer.parseInt(keyword) > 0) {
					predicateList.add(cb.like(root.get(Employee_.salary).as(String.class), likePattern));
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			predicateList.add(cb.like(root.get(Employee_.username), likePattern));
			predicateList.add(cb.like(root.get(Employee_.address), likePattern));
			predicateList.add(cb.like(root.get(Employee_.passport), likePattern));
			predicateList.add(cb.like(department.get(Department_.name), likePattern));
			predicateList.add(cb.like(position.get(Position_.name), likePattern));
			predicateList.add(cb.like(myUserAccount.get(MyUserAccount_.email), likePattern));
			predicateList.add(cb.like(myUserAccount.get(MyUserAccount_.active), likePattern));

		}
		
		return predicateList.toArray(size -> new Predicate[size]);
	}
	
}







