package com.controller;

import com.dto.AdminDto;
import com.dto.MusicDto;
import com.dto.PlayListDto;
import com.service.ServiceClass;
import com.view.ViewClass;
import com.dto.MemberDto;

import java.util.List;

public class Controller {
    private ViewClass dView = new ViewClass();
    private ServiceClass dServ = new ServiceClass();


    // 기본 실행 메소드
    public void run() {
        int menu = -1;

        while (true) {
            menu = dView.showFirst();

            if (menu == 0) {
                dView.printMsg("PLUM 프로그램 종료");
                break;
            }

            switch (menu) {
                case 1:
                    login();
                    break;
                case 2:
                    singUp();
                    break;
                case 3:
                    findPW();
                    break;
                case 4:
                    managLogin();
                    break;
            }

        }
    }


    // 관리자 계정 실행 메소드
    public void rusAsAdministrator() {

        int menu = -1;

        while (true) {
            menu = dView.showAdminMenu();

            if (menu == 0) {
                dView.printMsg("프로그램 종료");
                break;
            }

            switch (menu) {
                case 1:
                    insertMusicData();
                    break;
                case 2:
                    updateMusicData();
                    break;
                case 3:
                    deleteMusicData();
                    break;
                case 4:
                    outputMusicData();
                    break;
                default:
                    dView.printMsg("0~4 사이 숫자 입력!");
                    break;
            }
        }

    }

    // 로그인 이후 실행 메소드
    public void runAsMember(MemberDto profil) {
        int menu = -1;
        while (true) {

            // 로그인한 회원의 플레이리스트 중 가장 상단에 있는 노래를 가져오는 메소드
            MusicDto music = new MusicDto(); // 플레이리스트에서 가장 마지막에 추가된 노래의 정보를 담을 인스턴스 생성
            music = dServ.getPlayList(profil);

            menu = dView.showSubMenu(music);

            if (menu == 0) {
                dView.printMsg("프로그램 종료");
                break;
            }

            switch (menu) {
                case 1:
                    searchMusic(profil);
                    break;
                case 2:
                    latestMusic();
                    break;
                case 3:
                    popularMusic();
                    break;
                case 4:
                    showPlayList(profil);
                    break;
                default:
                    dView.printMsg("0~4 사이 숫자 입력!");
                    break;
            }
        }
    }


    /* run */

    // 회원 계정 로그인 메소드
    private void login() {
        MemberDto memData = new MemberDto();
        dView.login(memData);
        String msg = dServ.loginData(memData);
        dView.printMsg(msg);
        if (!msg.equals("로그인에 실패하셨습니다.")) {
            runAsMember(memData);
        }
    }

    // 회원가입 메소드
    private void singUp() {
        MemberDto memData = new MemberDto();
        String err = dView.signUp(memData);
        if (!err.equals("회원가입 실패")) {
            String msg = dServ.insertData(memData);
            dView.printMsg(msg);
        } else {
            dView.printMsg(err);
        }

    }

    // 비밀번호 찾기 메소드
    private void findPW() {
        MemberDto memData = new MemberDto();
        dView.findPW(memData);
        String msg = dServ.findPwData(memData);
        dView.printMsg(msg);
    }

    // 관리자 계정 로그인 메소드
    private void managLogin() {
        AdminDto aDto = new AdminDto();
        dView.managLogin(aDto);
        String msg = dServ.mLoginData(aDto);
        dView.printMsg(msg);
        if (msg.equals("관리자로 로그인되었습니다.")) {
            rusAsAdministrator();
        }

    }


    /* RunAsAdministrator */

    // 음악 정보 추가 메소드
    private void insertMusicData() {

        MusicDto mData = new MusicDto();
        dView.inputMusicData(mData);
        String msg = dServ.insertMusicData(mData);
        dView.printMsg(msg);
    }


    // 음악 정보 수정 메소드
    private void updateMusicData() {
        int code = dView.searchMusicTitle("수정할 음악 코드 : ");
        // 입력한 음악 코드에 해당되는 데이터를 [SERVICE]를 통해 가져온다
        MusicDto mData = dServ.getMusicData(code);
        // 가져온 데이터를 출력해서 수정하려는 데이터와 일치하는지 확인한다
        dView.outputSearchMusicData(mData);
        // mData가 Null이 아닌 경우(즉, 데이터가 있는 경우)
        if (mData != null) {
            // 수정할 데이터 입력 -> [VIEW]
            dView.updateMusicData(mData);
            // DB를 수정하기 위해 [SERVICE]로 전달하고 결과 메시지 출력
            String msg = dServ.updateMusicData(mData);
            // 결과 메시지 출력 -> [VIEW]
            dView.printMsg(msg);
        }
    }

    // 음악 정보 삭제 메소드
    private void deleteMusicData() {
        // 검섹 : Music 테이블의 기본키인 (m_code)를 통해서 찾아오기
        int code = dView.searchMusicTitle("삭제할 음악 코드 : ");
        // 입력한 음악 코드에 해당되는 데이터를 [SERVICE]를 통해 가져온다
        MusicDto mData = dServ.getMusicData(code);
        // 가져온 데이터를 출력해서 삭제하려는 데이터가 맞는지 확인한다
        if (mData != null) {
            dView.outputSearchMusicData(mData);
            String yn = dView.selectDeleteYN("삭제하시겠습니까? [Y/N] ");
            String msg = dServ.deleteMusicData(yn, code);
            dView.printMsg(msg);
        }
    }

    // 음악 목록 출력 메소드
    private void outputMusicData() {
        // [SERVICE]로 음악 테이블의 전체 데이터를 가져오는 메소드, 반환되는 타입은 목록 형태 ArrayList 활용
        List<MusicDto> mList = dServ.getMusicList();
        dView.outputMusicList(mList);
    }

    /* RunAsMember */

    // 음악 검색 메소드 (중복 허용)
    private void searchMusic(MemberDto memdata) {
        // 검색할 음악의 제목을 [VIEW]에서 입력받아서 가져오기
        String searchIndex = dView.searchMusic("검색 : ");
        List<MusicDto> mList = dServ.getSearchMusicList(searchIndex);
        MusicDto mData = dView.outputSearchList(mList,searchIndex);
        if (mData != null) {
            String msg = dServ.insertPlayList(mData, memdata);
            dView.printMsg(msg);
        }
    }

    // 최신 음악 출력 메소드
    private void latestMusic() {
        List<MusicDto> mList = dServ.getLatestMusicList();
        dView.outputLatestMusicList(mList);
    }

    // 인기 차트 출력 메소드
    private void popularMusic() {
        List<MusicDto> mList = dServ.getPopularMusicList();
        dView.outputPopularMuiscList(mList);
    }

    // 재생 목록 출력 메소드
    private void showPlayList(MemberDto profil) {
        List<MusicDto> mList = dServ.showPlayList(profil);
        dView.outputPlayList(mList);
    }



}
