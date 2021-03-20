# Automatically generated by boost-vcpkg-helpers/generate-ports.ps1

vcpkg_from_github(
    OUT_SOURCE_PATH SOURCE_PATH
    REPO boostorg/assign
    REF boost-1.73.0
    SHA512 69e75e42176a40bea8a208f2bebb4701678f1410aacd647a19f4d55aa12c851f53ee359e895af93dbb67ba3ec25a47eac871c54a8d645017a615fc810bd60ba7
    HEAD_REF master
)

include(${CURRENT_INSTALLED_DIR}/share/boost-vcpkg-helpers/boost-modular-headers.cmake)
boost_modular_headers(SOURCE_PATH ${SOURCE_PATH})