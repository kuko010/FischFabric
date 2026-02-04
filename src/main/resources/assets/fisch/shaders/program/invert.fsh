#version 110

uniform sampler2D DiffuseSampler;

varying vec2 texCoord;

void main() {
    vec4 color = texture2D(DiffuseSampler, texCoord);

    // Invert RGB channels, keep alpha unchanged
    gl_FragColor = vec4(1.0 - color.rgb, color.a);
}
