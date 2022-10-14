package com.view;

import com.dto.AdminDto;
import com.dto.MusicDto;
import com.dto.MemberDto;

import java.util.List;

public class ViewClass {
    private InOutClass ioc = new InOutClass();

    // 시작 화면 출력 메소드
    public int showFirst() {
        int menu = -1;

        ioc.twoPrint("====================================");
        ioc.twoPrint("\t\t\t🍑PLUM MUSIC");
        ioc.twoPrint("====================================");
        ioc.twoPrint("1. 로그인");
        ioc.twoPrint("2. 회원가입");
        ioc.twoPrint("3. 비밀번호 찾기");
        ioc.twoPrint("4. 관리자 로그인");
        ioc.twoPrint("0. Quit");

        menu = ioc.inNum("입력> ");

        return menu;

    }

    public String signUp(MemberDto memData) {
        String err = null;
        ioc.twoPrint("====================================");
        ioc.twoPrint("회원가입을 진행합니다 >>>>");
        ioc.twoPrint("====================================");
        memData.setM_id(ioc.inStr("아이디 : "));
        memData.setM_pwd(ioc.inStr("비밀번호 : "));
        memData.setM_name(ioc.inStr("이름 : "));
        memData.setM_age(ioc.inNum("나이 : "));
        if (memData.getM_age() == -1) {
            err = "회원가입 실패";
        } else {
            err = "회원가입 성공";
        }
        return err;
    }

    public void login(MemberDto memData) {
        ioc.twoPrint("====================================");
        ioc.twoPrint("로그인을 진행합니다 >>>>");
        ioc.twoPrint("====================================");
        memData.setM_id(ioc.inStr("아이디 : "));
        memData.setM_pwd(ioc.inStr("비밀번호 : "));
    }

    public void findPW(MemberDto memData) {
        ioc.twoPrint("====================================");
        ioc.twoPrint("비밀번호 찾기 >>>>");
        ioc.twoPrint("====================================");
        memData.setM_id(ioc.inStr("아이디 : "));
        memData.setM_name(ioc.inStr("이름 : "));
    }

    public void managLogin(AdminDto aDto) {
        ioc.twoPrint("====================================");
        ioc.twoPrint("관리자 계정으로 접속합니다 >>>>");
        ioc.twoPrint("====================================");
        aDto.setA_id(ioc.inStr("아이디 : "));
        aDto.setA_pwd(ioc.inStr("비밀번호 : "));
    }


    public int showSubMenu(MusicDto music) {
        int menu = -1;
        ioc.twoPrint("====================================");
        ioc.twoPrint("\t\t\t  🍑PLUM");
        if (music != null) {
            ioc.twoPrint("\t\t▶\t" + music.getM_title());
            ioc.twoPrint("\t\t-\t" + music.getM_singer() + "\t-");
            ioc.twoPrint("\t\t\t⏪\t⏯\t⏩");
        } else {
            ioc.twoPrint("\t현재 재생중인 음악이 없습니다");
        }
        ioc.twoPrint("====================================");
        ioc.twoPrint("1. 음악검색");
        ioc.twoPrint("2. 최신음악");
        ioc.twoPrint("3. 인기차트");
        ioc.twoPrint("4. 재생목록");
        ioc.twoPrint("0. 로그아웃");
        menu = ioc.inNum("입력 > ");
        return menu;
    }

    public void printMsg(String str) {
        ioc.twoPrint(str);
    }

    public String searchMusic(String s) {
        String str = null;
        ioc.twoPrint("🔍\t[제목] 또는 [가수]");
        ioc.twoPrint("====================================");
        str = ioc.inStr(s);
        return str;
    }

    public MusicDto outputSearchList(List<MusicDto> mList, String s) {
        MusicDto mData = new MusicDto();
        if (mList.size() == 0) {
            ioc.twoPrint("ERROR 404 '" + s + "' 대한 검색 결과가 없습니다");
            ioc.twoPrint("===============================================================");
            mData = null;
        } else {
            ioc.twoPrint("========================================================================================");
            ioc.twoPrint("\t\t\t\t\t\t\t💿Search Results");
            ioc.twoPrint("========================================================================================");
            ioc.twoPrint("번호\t|\t제목\t\t\t가수\t\t\t\t발매일\t\t\t앨범");
            ioc.twoPrint("========================================================================================");
            // 목록 출력(반복)
            for (MusicDto m : mList) {
                ioc.twoPrint((mList.indexOf(m) + 1) + "\t|\t" + m.getM_title() + "\t\t" + m.getM_singer() + "\t" + m.getM_date() + "\t\t" + m.getM_album());
                ioc.twoPrint("---------------------------------------------------------------");
            }
            ioc.twoPrint("\t\t\t\t\t\t\t🍑PLUM");
            ioc.twoPrint("========================================================================================");
            int menu = ioc.inNum("재생할 음악의 번호를 입력 : ");
            mData = mList.get(menu - 1);
        }
        return mData;
    }

    public int showAdminMenu() {
        int m = -1;
        ioc.twoPrint("====================================");
        ioc.twoPrint("\t\t\t🍑PLUM MUSIC");
        ioc.twoPrint("====================================");
        ioc.twoPrint("1. 음악추가");
        ioc.twoPrint("2. 음악수정");
        ioc.twoPrint("3. 음악삭제");
        ioc.twoPrint("4. 음악목록");
        ioc.twoPrint("0. 로그아웃");
        m = ioc.inNum("입력 > ");
        return m;
    }

    public void inputMusicData(MusicDto mData) {
        ioc.twoPrint("===============================");
        ioc.twoPrint("음악 정보를 입력합니다 >>>>");
        ioc.twoPrint("===============================");
        mData.setM_title(ioc.inStr("제목 : "));
        mData.setM_singer(ioc.inStr("가수 : "));
        mData.setM_date(ioc.inStr("발매일(yyyy-mm-dd) : "));
        mData.setM_album(ioc.inStr("앨범 : "));
    }

    public int searchMusicTitle(String s) {
        int code = 0;
        ioc.twoPrint("<음악 정보 검색>");
        ioc.twoPrint("===============================");
        code = ioc.inNum(s);
        return code;
    }

    public void outputSearchMusicData(MusicDto mData) {
        ioc.twoPrint("<수행 결과>");
        ioc.twoPrint("===============================================================");
        if (mData == null) {
            ioc.twoPrint("수행 결과 없음");
        } else {
            ioc.twoPrint("\t" + mData.getM_code() + "\t\t" + mData.getM_title() + "\t" + mData.getM_singer() + "\t" + mData.getM_date() + "\t" + mData.getM_album());
            ioc.twoPrint("===============================================================");
        }
    }


    public void updateMusicData(MusicDto mData) {
        ioc.twoPrint("===============================");
        ioc.twoPrint("수정할 내용이 없으면 엔터를 누르시오 >>>>");
        ioc.twoPrint("===============================");
        String ustr = ioc.inStr("제목 : ");
        if (!ustr.equals("")) {
            mData.setM_title(ustr);
        }
        ustr = ioc.inStr("가수 : ");
        if (!ustr.equals("")) {
            mData.setM_singer(ustr);
        }
        ustr = ioc.inStr("발매일(yyyy-mm-dd) : ");
        if (!ustr.equals("")) {
            mData.setM_date(ustr);
        }
        ustr = ioc.inStr("앨범 : ");
        if (!ustr.equals("")) {
            mData.setM_album(ustr);
        }
    }

    public String selectDeleteYN(String s) {
        String yn = ioc.inStr(s);
        return yn;
    }

    public void outputMusicList(List<MusicDto> mList) {
        if (mList.size() == 0) {
            ioc.twoPrint("ERROR 404 데이터가 없습니다");
            ioc.twoPrint("===============================================================");
            return;
        }
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("\t\t\t\t\t🗂 MUSIC LIST");
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("M.Code\t제목\t\t\t가수\t\t\t발매일\t\t앨범");
        ioc.twoPrint("===============================================================");
        // 목록 출력(반복)
        for (MusicDto m : mList) {
            ioc.twoPrint(m.getM_code() + "\t\t" + m.getM_title() + "\t" + m.getM_singer() + "\t\t" + m.getM_date() + "\t" + m.getM_album());
            ioc.twoPrint("---------------------------------------------------------------");
        }
        ioc.twoPrint("\t\t\t\t\t🗂 MUSIC LIST");
        ioc.twoPrint("===============================================================");
    }

    public void outputLatestMusicList(List<MusicDto> mList) {
        if (mList.size() == 0) {
            ioc.twoPrint("ERROR 404 데이터가 없습니다");
            ioc.twoPrint("===============================================================");
            return;
        }
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("\t\t\t\t\t📱 최신음악 TOP 10");
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("번호\t| 제목\t\t\t가수\t\t\t발매일\t\t앨범");
        ioc.twoPrint("===============================================================");
        // 목록 출력(반복)
        for (MusicDto m : mList) {
            ioc.twoPrint((mList.indexOf(m) + 1) + "\t| " + m.getM_title() + "\t" + m.getM_singer() + "\t\t" + m.getM_date() + "\t" + m.getM_album());
            ioc.twoPrint("---------------------------------------------------------------");
        }
        ioc.twoPrint("\t\t\t\t\t🗂 MUSIC LIST");
        ioc.twoPrint("===============================================================");
    }


    public void outputPopularMuiscList(List<MusicDto> mList) {
        if (mList.size() == 0) {
            ioc.twoPrint("ERROR 404 데이터가 없습니다");
            ioc.twoPrint("===============================================================");
            return;
        }
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("\t\t\t\t\t🔥 인기차트 TOP 10");
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("번호\t| 제목\t\t\t가수\t\t\t발매일\t\t앨범");
        ioc.twoPrint("===============================================================");
        // 목록 출력(반복)
        for (MusicDto m : mList) {
            ioc.twoPrint((mList.indexOf(m) + 1) + "\t| " + m.getM_title() + "\t" + m.getM_singer() + "\t\t" + m.getM_date() + "\t" + m.getM_album());
            ioc.twoPrint("---------------------------------------------------------------");
        }
        ioc.twoPrint("\t\t\t\t\t🗂 MUSIC LIST");
        ioc.twoPrint("===============================================================");
    }

    public void outputPlayList(List<MusicDto> mList) {
        if (mList.size() == 0) {
            ioc.twoPrint("현재 재생목록이 존재하지 않습니다.");
            ioc.twoPrint("===============================================================");
            return;
        }
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("\t\t\t\t\t🔥 나의 재생목록");
        ioc.twoPrint("===============================================================");
        ioc.twoPrint("번호\t| 제목\t\t\t가수\t\t\t앨범");
        ioc.twoPrint("===============================================================");
        for(MusicDto m : mList){
            ioc.twoPrint(mList.indexOf(m) + 1 +"\t| " + m.getM_title() + "\t" + m.getM_singer() +"\t\t" + m.getM_album() );

        }
        ioc.twoPrint("\t\t\t\t\t🗂 MUSIC LIST");
        ioc.twoPrint("===============================================================");
    }
}
