package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.*;
import ec.com.newsolutions.service.dto.*;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserMapper {

    private final AuthorityMapper authorityMapper;
    private final OrganizationMapper organizationMapper;
    private final BranchOfficeMapper branchOfficeMapper;
    private final PermitMapper permitMapper;

    public UserMapper(AuthorityMapper authorityMapper, OrganizationMapper organizationMapper, BranchOfficeMapper branchOfficeMapper, PermitMapper permitMapper) {
        this.authorityMapper = authorityMapper;
        this.organizationMapper = organizationMapper;
        this.branchOfficeMapper = branchOfficeMapper;
        this.permitMapper = permitMapper;
    }

    public List<UserDTO> usersToUserDTOs(List<User> users) {
        return users.stream()
            .filter(Objects::nonNull)
            .map(this::userToUserDTO)
            .collect(Collectors.toList());
    }

    public UserDTO userToUserDTOLight(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setIdentification(user.getIdentification());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setActivated(user.isActivated());
        userDTO.setLangKey(user.getLangKey());
        userDTO.setImageUrl(user.getImageUrl());
        return userDTO;
    }

    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setIdentification(user.getIdentification());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setPhone(user.getPhone());
        userDTO.setActivated(user.isActivated());
        userDTO.setLangKey(user.getLangKey());
        userDTO.setImageUrl(user.getImageUrl());
        userDTO.setAuthorities(entitySetToEntityList(authorityMapper,user.getAuthorities()));
        userDTO.setOrganizations(entitySetToEntityList(organizationMapper,user.getOrganizations()));
        userDTO.setBranchOffices(entitySetToEntityList(branchOfficeMapper,user.getBranchOffices()));
        userDTO.setPermits(entitySetToEntityList(permitMapper, user.getAuthorities().stream().map(Authority::getPermits).flatMap(x->x.stream()).collect(Collectors.toSet())));

        return userDTO;
    }


    public List<User> userDTOsToUsers(List<UserDTO> userDTOs) {
        return userDTOs.stream()
            .filter(Objects::nonNull)
            .map(this::userDTOToUser)
            .collect(Collectors.toList());
    }

    public User userDTOToUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        } else {
            User user = new User();
            user.setId(userDTO.getId());
            user.setIdentification(userDTO.getIdentification());
            user.setEmail(userDTO.getEmail().toLowerCase());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setPhone(userDTO.getPhone());
            user.setActivated(userDTO.isActivated());
            user.setLangKey(userDTO.getLangKey());
            user.setImageUrl(userDTO.getImageUrl());
            user.setAuthorities(entityListToAuthoritySet(authorityMapper,userDTO.getAuthorities()));
            user.setOrganizations(entityListToAuthoritySet(organizationMapper,userDTO.getOrganizations()));
            user.setBranchOffices(entityListToAuthoritySet(branchOfficeMapper,userDTO.getBranchOffices()));
            return user;
        }
    }


    public User userFromId(Long id) {
        if (id == null) {
            return null;
        }
        User user = new User();
        user.setId(id);
        return user;
    }

    private <T> Set<T> entityListToAuthoritySet(Object mapper, List list) {
        if (list == null) return null;

        Set<T> set = new HashSet<T>(Math.max((int) (list.size() / .75f) + 1, 16));
        setSet(mapper,list,set);

        return set;
    }

    private <T> List<T> entitySetToEntityList(Object mapper, Set set) {
        if ( set == null ) return null;

        List<T> list = new ArrayList<>( set.size() );
        setList(mapper,set,list);

        return list;
    }

    private void setSet(Object mapper, List list, Set set){

        if(mapper instanceof AuthorityMapper){
            for ( Object object : list ) {
                set.add( authorityMapper.toEntity((AuthorityDTO) object) );
            }
        }else if(mapper instanceof OrganizationMapper){
            for ( Object object : list ) {
                set.add( organizationMapper.toEntity((OrganizationDTO) object) );
            }
        }else if(mapper instanceof BranchOfficeMapper){
            for ( Object object : list ) {
                set.add( branchOfficeMapper.toEntity((BranchOfficeDTO) object) );
            }
        }else if(mapper instanceof PermitMapper){
            for ( Object object : list ) {
                set.add( permitMapper.toEntity((PermitDTO) object) );
            }
        }
    }

    private void setList(Object mapper, Set set, List list){

        if(mapper instanceof AuthorityMapper){
            for ( Object entity : set ) {
                list.add( authorityMapper.toDtoLight((Authority) entity) );
            }
        }else if(mapper instanceof OrganizationMapper){
            for ( Object entity : set ) {
                list.add( organizationMapper.toDto((Organization) entity) );
            }
        }else if(mapper instanceof BranchOfficeMapper){
            for ( Object entity : set ) {
                list.add( branchOfficeMapper.toDtoLight((BranchOffice) entity) );
            }
        }else if(mapper instanceof PermitMapper){
            for ( Object entity : set ) {
                list.add( permitMapper.toDto((Permit) entity) );
            }
        }
    }
}
