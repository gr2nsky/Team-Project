package com.ysms.command;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.ysms.common.FilePath;
import com.ysms.common.ReservationInfo;
import com.ysms.dao.Dao_Reservation;
import com.ysms.dao.Dao_Share;
import com.ysms.dto.Dto_Refine_rental;
import com.ysms.dto.Dto_Refine_rentalDetail;
import com.ysms.dto.Dto_Reservation_rental;
import com.ysms.dto.Dto_Reservation_rentalDetail;
import com.ysms.dto.Dto_Share;


public class ContentCommand implements Command {

	FilePath file = new FilePath();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int no = Integer.parseInt(request.getParameter("no"));

		Dao_Share dao = new Dao_Share();
		Dto_Share dto = dao.detail(no);
		
		System.out.println(no);
		//파일이 업로드되있는 상태라면 (이름+경로) - 경로를 해서 파일 이름만 추출해서 request로 전송
		if(dto.getFilePath() != null) {
			String filePath = dto.getFilePath();
			String fileName = filePath.substring(file.Image.length());
			request.setAttribute("fileName", fileName);
		}
		request.setAttribute("DETAIL", dto);
		System.out.println("@@@@@@@@@@@@@dayLimit : " + dto.getDayLimit());
		request.setAttribute("dayLimit", dto.getDayLimit());
		System.out.println(no);
		
		//@@@@@@@@@@@@@@@@@@@추가 2021 05 27 윤재필 - 예약확인
		int sNo = no;
		Dto_Share detail = dto;
		
		DecimalFormat df = new DecimalFormat("00");
        Calendar currentCalendar = Calendar.getInstance();
        int month  = Integer.parseInt(df.format(currentCalendar.get(Calendar.MONTH) + 1));
		
        //dto를 이번달과 다음달로 분류
		ArrayList<Dto_Reservation_rentalDetail> thisMonthDtos = new ArrayList<Dto_Reservation_rentalDetail>();
		ArrayList<Dto_Reservation_rentalDetail> nextMonthDtos = new ArrayList<Dto_Reservation_rentalDetail>();
		
		// 예약일자,Dto_Reservation_rental 쌍의 LinkedHashMap : 순서를 보장한다. 정렬후 사용하므로 순서 보장이 되는쪽이 효율
		LinkedHashMap<Integer, Dto_Refine_rentalDetail> thisMonthResMap = 
				new LinkedHashMap<Integer, Dto_Refine_rentalDetail>();
		LinkedHashMap<Integer, Dto_Refine_rentalDetail> nextMonthResMap = 
				new LinkedHashMap<Integer, Dto_Refine_rentalDetail>();
		
		//map을 jsonArray로 변환
		JSONArray thisMonthResData = new JSONArray();
		JSONArray nextMonthResData = new JSONArray();
		
		//예약이 존재하는 날자 저
		ArrayList<Integer> thisMonthResDate = new ArrayList<Integer>();
		ArrayList<Integer> nextMonthResDate = new ArrayList<Integer>();

		// 성공적으로 로드를 못할 때도 존재하니 no 일치하는지 검사
		if (sNo == detail.getNo()) {
			detail.printAll();
			Dao_Reservation resDao = new Dao_Reservation();
			ArrayList<Dto_Reservation_rentalDetail> resDtos = resDao.refineSharesDetail(detail.getPlace_no());
			
			//dto를 이번달과 다음달로 분류
			for(Dto_Reservation_rentalDetail resDto : resDtos) {
				System.out.println("이번달 : " + month + "   dto의 달 : " + resDto.getMonth());
				if (resDto.getMonth() == month) {
					thisMonthDtos.add(resDto);
				} else {
					nextMonthDtos.add(resDto);
				}
			}
			
			//각 dtos에서 예약일자 추출해 하나의 날자로 통합
			for (Dto_Reservation_rentalDetail resDto : thisMonthDtos) {
				Dto_Refine_rentalDetail refineData;
				//예약일자가 map에 존재하지 않는다면 추가
				if (thisMonthResMap.containsKey(resDto.getDate()) == false) {
					//map에 넣을 dto 생성
					refineData = new Dto_Refine_rentalDetail();
				} else {
					//이 경우는 이미 map에 존재하므로, 불러온다
					refineData = thisMonthResMap.get(resDto.getDate());
				}
				refineData.insertData(resDto.getNo(), resDto.getResName(), resDto.getResEmail(), resDto.getResPhone(),
						resDto.getResCapacity(), resDto.getResPrice(), detail.getStartTime(), resDto.getStartTime(), resDto.getUsingTime());
				//해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
				thisMonthResMap.put(resDto.getDate(), refineData);
			}
			
			for (Dto_Reservation_rentalDetail resDto : nextMonthDtos) {
				Dto_Refine_rentalDetail refineData;
				//예약일자가 map에 존재하지 않는다면 추가
				if (nextMonthResMap.containsKey(resDto.getDate()) == false) {
					//map에 넣을 dto 생성
					refineData = new Dto_Refine_rentalDetail();
				} else {
					//이 경우는 이미 map에 존재하므로, 불러온다
					refineData = nextMonthResMap.get(resDto.getDate());
				}
				refineData.insertData(resDto.getNo(), resDto.getResName(), resDto.getResEmail(), resDto.getResPhone(),
						resDto.getResCapacity(), resDto.getResPrice(), detail.getStartTime(), resDto.getStartTime(), resDto.getUsingTime());
				//해당 키값의 벨류를 갱신시켜준다. (put은 갱신 및 추가 둘다 사용 가능
				nextMonthResMap.put(resDto.getDate(), refineData);
			}
			
			//map에서 json으로 변환
			for(Map.Entry<Integer, Dto_Refine_rentalDetail> refineData :thisMonthResMap.entrySet()) {
				Dto_Refine_rentalDetail resDto = refineData.getValue();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("date", refineData.getKey());
				jsonObject.put("no", resDto.getNo());
				jsonObject.put("resName", resDto.getResName());
				jsonObject.put("resEmail", resDto.getResEmail());
				jsonObject.put("resPhone", resDto.getResPhone());
				jsonObject.put("resCapacity", resDto.getResCapacity());
				jsonObject.put("resPrice", resDto.getResPrice());
				jsonObject.put("startNum", resDto.getStartNum());
				jsonObject.put("shareTime", resDto.getShareTime());
				jsonObject.put("totalShareTime", resDto.getTotalShareTime());
				thisMonthResData.add(jsonObject);
				
				if( 0 < resDto.getTotalShareTime()) {
					System.out.println("java 예약 있는날: " + refineData.getKey());
					thisMonthResDate.add(refineData.getKey());
				}
			}
			
			for(Map.Entry<Integer, Dto_Refine_rentalDetail> refineData :nextMonthResMap.entrySet()) {
				Dto_Refine_rentalDetail resDto = refineData.getValue();
				
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("date", refineData.getKey());
				jsonObject.put("no", resDto.getNo());
				jsonObject.put("resName", resDto.getResName());
				jsonObject.put("resEmail", resDto.getResEmail());
				jsonObject.put("resPhone", resDto.getResPhone());
				jsonObject.put("resCapacity", resDto.getResCapacity());
				jsonObject.put("resPrice", resDto.getResPrice());
				jsonObject.put("startNum", resDto.getStartNum());
				jsonObject.put("shareTime", resDto.getShareTime());
				jsonObject.put("totalShareTime", resDto.getTotalShareTime());
				thisMonthResData.add(jsonObject);
				
				if( 0 < resDto.getTotalShareTime()) {
					System.out.println("java 예약 있는날: " + refineData.getKey());
					nextMonthResDate.add(refineData.getKey());
				}
			}
			
			// dto를 이번달과 다음달로 나눠서 전송
			request.setAttribute("thisMonthResData", thisMonthResData);
			request.setAttribute("nextMonthResData", nextMonthResData);
			
			Integer[] thisMonthResDateList = thisMonthResDate.toArray(new Integer[thisMonthResDate.size()]);
			Integer[] nextMonthResDateList = nextMonthResDate.toArray(new Integer[nextMonthResDate.size()]);
			
			request.setAttribute("thisMonthResDateList", thisMonthResDateList);
			request.setAttribute("nextMonthResDateList", nextMonthResDateList);
			request.setAttribute("error", 0);
			System.out.println("place no이 동일하므로 다음작업을 수행합니다.");
		} else {
			System.out.println("시스템 오류. 데이터를 로드하는데 문제가 발생했습니다.");
			//error 전송
			request.setAttribute("error", 1);
		}

	}
	

}
