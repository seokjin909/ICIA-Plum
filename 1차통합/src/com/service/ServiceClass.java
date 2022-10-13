package com.service;

import com.dao.MemberDao;
import com.dao.MusicDao;
import com.dao.PlayListDao;
import com.dto.AdminDto;
import com.dto.MemberDto;
import com.dto.MusicDto;
import com.dto.PlayListDto;

import java.util.List;

public class ServiceClass {

    private MusicDao muDao = new MusicDao();
    private PlayListDao pDao = new PlayListDao();
    private MemberDao dDao = new MemberDao();

    public String insertData(MemberDto memData) {
        String msg = null;

        int res = dDao.insertData(memData);

        if (res == 0) {
            msg = "회원가입 실패";
        } else {
            msg = "회원가입 성공";
        }
        return msg;
    }

    public String loginData(MemberDto memData) {
        String msg = null;

        MemberDto mData = dDao.loginData(memData);

        if (mData == null) {
            msg = "로그인에 실패하셨습니다.";
        } else {
            msg = mData.getM_name() + "님 로그인에 성공하셨습니다.";
        }

        return msg;
    }

    public String findPwData(MemberDto memData) {
        String msg = null;

        MemberDto mData = dDao.findPwData(memData);

        if (mData == null) {
            msg = "해당하는 아이디는 존재하지 않습니다.";
        } else {
            msg = "해당하는 아이디의 비밀번호는 " + mData.getM_pwd() + "입니다.";
        }

        return msg;
    }

    public String mLoginData(AdminDto aDto) {
        String msg = null;

        AdminDto adDto = dDao.mLogiData(aDto);

        if (adDto == null) {
            msg = "해당하는 관리자는 존재하지 않습니다.";
        } else {
            msg = "관리자로 로그인되었습니다.";
        }
        return msg;
    }

    public String insertMusicData(MusicDto mData) {
        String msg = null;

        // [DAO]에 데이터 삽입 처리 요구하기
        int res = muDao.insertMusicData(mData);
        if (res != 0) {
            msg = "추가 성공";
        } else {
            msg = "추가 실패";
        }
        return msg;
    }

    public MusicDto getMusicData(int code) {
        // 받은 음악 코드를 [DAO]에 전달하여 해당되는 데이터를 가져오도록 한다
        MusicDto mData = muDao.getSearchMusicData(code);
        return mData;
    }

    public String updateMusicData(MusicDto mData) {
        String msg = null;

        int res = muDao.updateMusicData(mData);
        if (res != 0) {
            msg = "수정 완료";
        } else {
            msg = "수정 실패";
        }
        return msg;
    }

    public String deleteMusicData(String yn, int code) {
        String msg = null;
        if (yn.equals("Y")) {
            int res = muDao.deleteMusicData(code);
            if (res > 0) {
                msg = "삭제 성공";
            } else {
                msg = "삭제 실패";
            }
        } else {
            msg = "취소되었습니다";
        }
        // [DAO]로 입력한 코드를 넘겨서 데이터 삭제 처리
        return msg;
    }

    public List<MusicDto> getMusicList() {
        // [DAO]의 전체 목록을 ArrayList에 저장
        List<MusicDto> mList = muDao.getMusicList();
        return mList;
    }

    public List<MusicDto> getSearchMusicList(String searchIndex) {
        List<MusicDto> mList = muDao.getSearchMusicList(searchIndex);
        return mList;
    }


    public String insertPlayList(MusicDto mData, MemberDto profil) {
        String msg = null;

        int res = pDao.insertMusicData(mData, profil);
        if (res != 0) {
            msg = "추가 완료";
        } else {
            msg = "추가 실패";
        }
        return msg;
    }


    public MusicDto getPlayList(PlayListDto pList, MemberDto profil) {
        MusicDto music = pDao.getFirstMusic(pList, profil);
        return music;
    }

    public List<MusicDto> getLatestMusicList() {
        // [DAO]의 전체 목록을 ArrayList에 저장
        List<MusicDto> mList = muDao.getLatestMusicList();
        return mList;
    }

    public List<MusicDto> getPopularMusicList() {
        List<MusicDto> mList = muDao.getPopularMusicList();
        return mList;
    }
}
